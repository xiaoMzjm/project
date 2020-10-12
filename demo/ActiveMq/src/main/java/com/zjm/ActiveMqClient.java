package com.zjm;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author:黑绝
 * @date:2017/9/2 17:30
 */
public class ActiveMqClient {

    //连接账号
    private static String userName = "";
    //连接密码
    private static String password = "";
    //连接地址
    //private static String brokerURL = "tcp://30.11.240.29:61616";
    private static String brokerURL = "tcp://30.39.45.94:61616";
    //connection的工厂
    private static ConnectionFactory factory;
    //连接对象
    private static Connection connection;

    static {
        try {
            factory = new ActiveMQConnectionFactory(userName, password, brokerURL);
            connection = factory.createConnection();
            connection.start();
        }catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }


    /**
     * 单点发送
     * @param queueName
     * @param message
     * @throws Exception
     */
    public static void sendSingleMsg(String queueName , Object message) throws Exception{
        Session session = null;
        MessageProducer producer = null;

        try{

            //创建一个session
            //第一个参数:是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
            //第一个参数为false时，第二个参数的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
            //Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功。
            //Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
            //DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //创建一个到达的目的地，其实想一下就知道了，activemq不可能同时只能跑一个队列吧，这里就是连接了一个名为"text-msg"的队列，这个会话将会到这个队列，当然，如果这个队列不存在，将会被创建
            Destination destination = session.createQueue(queueName);

            //从session中，获取一个消息生产者
            producer = session.createProducer(destination);

            //设置生产者的模式，有两种可选
            //DeliveryMode.PERSISTENT 当activemq关闭的时候，队列数据将会被保存
            //DeliveryMode.NON_PERSISTENT 当activemq关闭的时候，队列里面的数据将会被清空
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            //创建一条消息，当然，消息的类型有很多，如文字，字节，对象等,可以通过session.create..方法来创建出来
            ObjectMessage objectMessage = session.createObjectMessage((Serializable)message);
            producer.send(objectMessage);

            System.out.println("点对点发送消息:" + message.toString());

        }  finally {
            //即便生产者的对象关闭了，程序还在运行哦
            if(producer != null) {
                producer.close();
            }
            if(session != null) {
                session.close();
            }
        }
    }

    /**
     * 添加点对点监听器
     * @param queueName
     * @param listener
     * @throws JMSException
     */
    public static void addSingleListener(String queueName , MessageListener listener) throws JMSException {
        Session session = null;

        try {
            //创建一个session
            //第一个参数:是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
            //第二个参数为false时，paramB的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
            //Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功。
            //Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
            //DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //创建一个到达的目的地，其实想一下就知道了，activemq不可能同时只能跑一个队列吧，这里就是连接了一个名为"text-msg"的队列，这个会话将会到这个队列，当然，如果这个队列不存在，将会被创建
            Destination destination = session.createQueue(queueName);

            //根据session，创建一个接收者对象
            MessageConsumer consumer = session.createConsumer(destination);

            //实现一个消息的监听器
            //实现这个监听器后，以后只要有消息，就会通过这个监听器接收到
            consumer.setMessageListener(listener);


        }finally {
            if(session != null) {
                // session 不能关
                //session.close();
            }
        }
    }

    /**
     * 广播发送
     * @param topicName
     * @param message
     */
    public static void sendBroadcastMsg(String topicName , Object message) throws JMSException {
        Session session = null;
        MessageProducer producer  = null;
        try {
            session = connection.createSession(false , Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(topicName);
            producer = session.createProducer(topic);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            ObjectMessage objectMessage = session.createObjectMessage((Serializable)message);
            producer.send(objectMessage);
            System.out.println("广播发送消息：" + message.toString());
        }finally {
            if(session != null) {
                session.close();
            }
            if(producer != null) {
                producer.close();
            }
        }
    }

    public static void addBroadcastListener(String topicName , MessageListener listener) throws JMSException {
        Session session = null;

        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(topicName);
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(listener);
        }finally {
            if(session != null) {
                // session 不能关
                //session.close();
            }
        }
    }
}
