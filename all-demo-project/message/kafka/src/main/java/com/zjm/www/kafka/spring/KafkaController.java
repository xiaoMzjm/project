package com.zjm.www.kafka.spring;

import com.google.gson.Gson;
import com.zjm.www.kafka.message.PropertiesConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Resource
    KafkaTemplate<String,String> kafkaTemplate;

    /**
     * http://localhost/kafka/send?value=hello,my,name,is,heijue
     */
    @RequestMapping(value = "/send" ,  method = RequestMethod.GET)
    public String send(@RequestParam("value") String value) throws Exception{
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(PropertiesConfig.TOPIC, String.valueOf(Math.abs(value.hashCode()% PropertiesConfig.PATITIONS)) , value);
        Gson gson = new Gson();
        System.out.println("KafkaController#send，发送消息成功：" + gson.toJson(result.get()));
        return gson.toJson(result.get());
    }

    /**
     * http://localhost/kafka/maxStream?value=hello:7,world:6
     */
    @RequestMapping(value = "/maxStream" ,  method = RequestMethod.GET)
    public String maxStream(@RequestParam("value") String value) throws Exception{
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(PropertiesConfig.TOPIC_MAX_STREAM, String.valueOf(Math.abs(value.hashCode()% PropertiesConfig.PATITIONS)) , value);
        Gson gson = new Gson();
        System.out.println("KafkaController#maxStream，发送消息成功：" + gson.toJson(result.get()));
        return gson.toJson(result.get());
    }

    @Component
    public class KafkaMessageReceiver {
        @KafkaListener(topics = {PropertiesConfig.TOPIC}, groupId = PropertiesConfig.GROUP_ID , containerFactory = "listenerContainerFactory")
        public void registryReceiver(List<ConsumerRecord<String, String>> integerStringConsumerRecords) {
            Iterator<ConsumerRecord<String, String>> it = integerStringConsumerRecords.iterator();
            while (it.hasNext()){
                ConsumerRecord<String, String> consumerRecords = it.next();
                System.out.println(PropertiesConfig.TOPIC + "收到消息：" + consumerRecords.key() + " : " + consumerRecords.value());
            }
        }
    }

    @Component
    public class KafkaMessageReceiver3 {
        @KafkaListener(topics = {PropertiesConfig.TOPIC_MY_PROCESS}, groupId = PropertiesConfig.GROUP_ID , containerFactory = "listenerContainerFactory")
        public void registryReceiver(List<ConsumerRecord<String, Long>> integerStringConsumerRecords) {
            Iterator<ConsumerRecord<String, Long>> it = integerStringConsumerRecords.iterator();
            while (it.hasNext()){
                ConsumerRecord<String, Long> consumerRecords = it.next();
                System.out.println(PropertiesConfig.TOPIC_MY_PROCESS + "收到消息：" + consumerRecords.key() + " : " + consumerRecords.value());
            }
        }
    }
}
