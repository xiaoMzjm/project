package com.zjm.www.message;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

public class Consumer {

    static class Subscribe {

        public static void main(String[] args) {
            // 1. 创建消费者
            Properties props = new Properties();
            props.put("bootstrap.servers", "localhost:9092");
            props.put("group.id", "test");
            props.put("client.id", "test");
            props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

            consumer.subscribe(Arrays.asList("heijue-topic2"),new ConsumerRebalanceListener() {

                // 一个是在消费者平衡操作开始之前，消费者停止拉取消息之后被调用
                public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                    consumer.commitSync();// 提交偏移量
                }
                // 另一个是在平衡之后、消费者开始拉取消息之前被调用
                public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                    long committedOffset=-1;
                    for(TopicPartition topicPartition:partitions){
                        // 获取该分区已消费的偏移量
                        committedOffset = consumer.committed(topicPartition).offset();
                        // 重置偏移量到上一次提交的偏移量下一个位置处开始消费
                        consumer.seek(topicPartition, committedOffset+1);
                    }
                }
            });

            Duration duration = Duration.ofHours(1);
            while(true) {
                // 自动提交策略由消费者协调器（ConsumerCoordinator）每隔${ auto.commit.interval.ms}毫秒执行一次偏移量的提交
                ConsumerRecords<String, String> records = consumer.poll(duration);
                for(ConsumerRecord<String ,String> record : records) {
                    System.out.println("partition="+ record.partition() +"，k=" + record.key() + ",value=" + record.value());
                }
            }

        }
    }


    static class OffSetAndPostion {

        public static void main(String[] args) {
            // 1. 创建消费者
            Properties props = new Properties();
            props.put("bootstrap.servers", "localhost:9092");
            props.put("group.id", "test");
            props.put("client.id", "test");
            props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

            TopicPartition topicPartition = new TopicPartition("heijue-topic" , 0);
            OffsetAndMetadata offsetAndMetadata = consumer.committed(topicPartition);
            System.out.println("offset = " + offsetAndMetadata.offset());
            System.out.println("metadata = " + offsetAndMetadata.metadata());
            System.out.println("leaderEpoch = " + offsetAndMetadata.leaderEpoch().get());

            long postion = consumer.position(topicPartition);
            System.out.println("postion = " + postion);

        }
    }
}
