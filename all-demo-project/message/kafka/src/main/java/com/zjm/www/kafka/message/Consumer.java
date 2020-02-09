package com.zjm.www.kafka.message;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Consumer {



    /**
     * 订阅
     */
    static class Subscribe {

        public static void main(String[] args) {
            // 1. 创建消费者
            final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(PropertiesConfig.getKafkaConsumerPro());

            consumer.subscribe(Arrays.asList(PropertiesConfig.TOPIC),new ConsumerRebalanceListener() {

                // 一个是在消费者平衡操作开始之前，消费者停止拉取消息之后被调用
                @Override
                public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                    System.out.println("提交偏移量 = " + new Gson().toJson(partitions));
                    consumer.commitSync();// 提交偏移量
                }
                // 另一个是在平衡之后、消费者开始拉取消息之前被调用
                @Override
                public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                    long committedOffset=-1;
                    for(TopicPartition topicPartition : partitions){
                        System.out.println("分区=" + topicPartition.partition() + " ，offset=" + consumer.committed(topicPartition).offset());
                        // 获取该分区已消费的偏移量
                        committedOffset = consumer.committed(topicPartition).offset();
                        // 重置偏移量到上一次提交的偏移量
                        consumer.seek(topicPartition, committedOffset );

                    }
                    // 重置所有分区到最后消费节点（有效，感觉线上一般用这个吧）
                    // consumer.seekToEnd(partitions);
                }
            });

            Duration duration = Duration.ofHours(1);
            while(true) {
                // 自动提交策略由消费者协调器（ConsumerCoordinator）每隔${ auto.commit.interval.ms}毫秒执行一次偏移量的提交
                ConsumerRecords<String, String> records = consumer.poll(duration);
                for(ConsumerRecord<String ,String> record : records) {
                    System.out.println("收到消息：" + new Gson().toJson(record));
                }
            }

        }
    }


    /**
     * 重新设置消费点
     */
    static class OffSetAndPostion {

        public static void main(String[] args) {
            // 1. 创建消费者
            final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(PropertiesConfig.getKafkaConsumerPro());

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
