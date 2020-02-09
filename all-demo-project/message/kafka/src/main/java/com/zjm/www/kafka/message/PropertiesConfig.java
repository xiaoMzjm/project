package com.zjm.www.kafka.message;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.*;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;

public class PropertiesConfig {

    public static final String BROKER_LIST = "127.0.0.1:9092";
    public static final String TOPIC = "heijue-topic";
    public static final String TOPIC_MY_PROCESS = "heijue-topic-process";
    public static final String TOPIC_MAX_STREAM = "heijue-topic-max-stream";
    public static final String TOPIC_MAX_STREAM_PROCESS = "heijue-topic-max-stream-process";

    public static final Integer PATITIONS = 3;
    public static final String GROUP_ID = "CID_heijue_consumer";
//    public static final String CLIENT_ID = "CID_heijue_consumer_client_id";

    private static final String APPLICATION_ID = "all-demo-project";
    private static final String APPLICATION_ID_2 = "all-demo-project2";

    private static final String AUTO_OFFSET_RESET_CONFIG = "latest";
    private static final Integer AUTO_COMMIT_INTERVAL_MS_CONFIG = 1000;



    // 消费者
    public static Properties getKafkaConsumerPro(){
        Properties props = new Properties();
        // kafka服务器地址
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        // 消费者组
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
//        props.put(ConsumerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        // 序列化和反序列化
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 自动提交偏移量
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, AUTO_COMMIT_INTERVAL_MS_CONFIG);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        // 当没有offset时，消费什么数据
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET_CONFIG);
        return props;
    }

    // 消费者
    public static Properties getKafkaConsumerSLPro(){
        Properties props = new Properties();
        // kafka服务器地址
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        // 消费者组
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
//        props.put(ConsumerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        // 序列化和反序列化
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        // 自动提交偏移量
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, AUTO_COMMIT_INTERVAL_MS_CONFIG);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        // 当没有offset时，消费什么数据
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET_CONFIG);
        return props;
    }

    // 生产者
    public static Properties getKafkaProducePro(){
        Properties props = new Properties();
        // kafka服务器地址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        // 序列化和反序列化
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG ,StringSerializer.class.getName());
        return props;
    }

    // 流
    public static Properties getKafkaStreamPro(){
        Properties props = new Properties();
        // 指定流处理应用的id，该配置必须指定
        props.put(StreamsConfig.APPLICATION_ID_CONFIG , APPLICATION_ID);
        // kafka服务器地址
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        // 序列化和反序列化
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());
        return props;
    }

    // 流
    public static Properties getKafkaStreamPro2(){
        Properties props = new Properties();
        // 指定流处理应用的id，该配置必须指定
        props.put(StreamsConfig.APPLICATION_ID_CONFIG , APPLICATION_ID_2);
        // kafka服务器地址
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        // 序列化和反序列化
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());
        return props;
    }

}
