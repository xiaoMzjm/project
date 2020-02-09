package com.zjm.www.kafka.message;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class Producer {

    /**
     * 单线程发送消息，多线程下，自己使用线程池共享一个KafkaProducer对象即可
     */
    static class SendMsg {

        public static void main(String[] args) throws Exception{
            Properties properties = PropertiesConfig.getKafkaProducePro();
            KafkaProducer producer = new KafkaProducer<String, String>(properties);

            User user = new User("小张");
            user.setDesc("你好，我是小张");

            // key相同的消息，会进入到同一个分区，即有序
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(PropertiesConfig.TOPIC, user.getDesc());
            Future<RecordMetadata> future = producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(exception != null) {
                        System.out.println("发送消息发生异常:" + exception.getMessage());
                    }
                }
            });
            RecordMetadata recordMetadata = future.get();
            System.out.println("偏移量="+recordMetadata.offset());
            System.out.println("分区="+recordMetadata.partition());
        }
    }
}
