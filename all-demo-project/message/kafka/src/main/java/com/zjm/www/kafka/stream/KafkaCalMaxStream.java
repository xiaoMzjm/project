package com.zjm.www.kafka.stream;

import com.zjm.www.kafka.message.PropertiesConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.WindowStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
public class KafkaCalMaxStream {

    @Autowired
    private KafkaStreams kafkaCalMaxStreams;

    @Bean("kafkaCalMaxStreams")
    public KafkaStreams kafkaCalMaxStreams(){
        System.out.println("准备启动kafkaCalMaxStreams流");

        // 流建造器
        StreamsBuilder builder = new StreamsBuilder();

        // 原始流
        KStream<String , String> source = builder.stream(PropertiesConfig.TOPIC_MAX_STREAM);

        source.filter((key , value) -> {
                        System.out.println("KafkaCalMaxStream#filter kafka stream 新的流，key=" + key +"，value=" + value );
                        return !StringUtils.isEmpty(value);})

                    .flatMapValues((value) -> {return Arrays.asList(value.split(",")) ;})

                    .map((key , value) -> {
                        String v = value.split(":")[1];
                        String k = value.split(":")[0];
                        return new KeyValue<>(k , v);
                    })

                    .groupByKey() // 根据key聚合

                    .windowedBy(TimeWindows.of(TimeUnit.SECONDS.toMillis(60)).advanceBy(TimeUnit.SECONDS.toMillis(60)))

                    .aggregate(
                            new Initializer<Integer>() {
                                @Override
                                public Integer apply() {
                                    return Integer.MIN_VALUE;
                                }
                            } ,
                            // 这里的kv要和properties中的序列化方式一样
                            new Aggregator<String, String, Integer>() {
                                @Override
                                public Integer apply(String aggKey, String newValue, Integer aggValue) {
                                    System.out.println("KafkaCalMaxStream#aggregate aggKey:" + aggKey+ ",  newValue:"  +  newValue +", aggValue:" + aggValue );
                                    return Integer.parseInt(newValue) > aggValue ? Integer.parseInt(newValue): aggValue;
                                }
                            },
                            Materialized.<String, Integer, WindowStore<Bytes, byte[]>>as("time-windowed-aggregated-temp-stream-store")
                                    .withValueSerde(Serdes.Integer())
                                    .withKeySerde(Serdes.String()))

                    .toStream()
                    .to(PropertiesConfig.TOPIC_MAX_STREAM_PROCESS);

        ;


        KafkaStreams streams = new KafkaStreams(builder.build(), PropertiesConfig.getKafkaStreamPro2());
        streams.start();

        System.out.println("启动kafkaCalMaxStreams流成功");

        return streams;

    }


    @PreDestroy
    public void after() {
        System.out.println("关闭kafkaCalMaxStreams流");
        kafkaCalMaxStreams.cleanUp();
        kafkaCalMaxStreams.close();
    }
}
