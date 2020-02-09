package com.zjm.www.kafka.stream;

import com.zjm.www.kafka.message.PropertiesConfig;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.Locale;

@Configuration
public class KafkaCountWordsStream {
    @Autowired
    private KafkaStreams kafkaCountWordsStreams;

    @Autowired
    private MyProcess myProcess;

    @Bean("kafkaCountWordsStreams")
    public KafkaStreams kafkaCountWordsStreams(){
        System.out.println("准备启动kafkaCountWordsStreams流");

        // 流建造器
        StreamsBuilder builder = new StreamsBuilder();

        // 原始流
        KStream<String , String> source = builder.stream(PropertiesConfig.TOPIC);

                    // 过滤空白行
        source.filter((key , value) -> {
                        System.out.println("kafka stream 新的流，key=" + key +"，value=" + value );
                        return !StringUtils.isEmpty(value);
                    })

                    // 把value拆分
                    .flatMapValues((value) -> {
                        return Arrays.asList(value.toLowerCase(Locale.getDefault()).split(","));
                    })

                    // 重新组成kv
                    .map((key , value) -> {
                        return new KeyValue<String , String>(value , value);
                    })

                    // 通过key聚合
                    .groupByKey()

                    // count，count结果暂存在count-store中（一个topic）
                    .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"))

                    // 转化成stream
                    .toStream().map((key,value)->{
                        return new KeyValue<String , String>(key , value.toString());
                    }).

                    // 添加处理器，可以自己保存到redis等存储
                    process(() ->{
                        return myProcess;
                    })
        ;


        KafkaStreams streams = new KafkaStreams(builder.build(), PropertiesConfig.getKafkaStreamPro());
        streams.start();

        System.out.println("启动kafkaCountWordsStreams流成功");

        return streams;

    }

    @Component
    static class MyProcess implements Processor<String,String> {

        @Autowired
        KafkaTemplate<String,String> kafkaTemplate;

        @Override
        public void init(ProcessorContext context) {
        }

        @Override
        public void process(String key, String value) {
            try{
                ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(PropertiesConfig.TOPIC_MY_PROCESS, key , value);
                // 一般存储到redis中
                //Gson gson = new Gson();
                //System.out.println("MyProcess#process，发送消息成功：key=" + key + " , value=" + value + " , result = " + gson.toJson(result.get()));
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public void close() {
        }
    }

    @PreDestroy
    public void after() {
        System.out.println("关闭kafkaCountWordsStreams流");
        kafkaCountWordsStreams.cleanUp();
        kafkaCountWordsStreams.close();
    }
}
