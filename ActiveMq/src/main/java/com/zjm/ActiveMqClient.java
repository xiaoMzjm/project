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
 * @author:�ھ�
 * @date:2017/9/2 17:30
 */
public class ActiveMqClient {

    //�����˺�
    private static String userName = "";
    //��������
    private static String password = "";
    //���ӵ�ַ
    //private static String brokerURL = "tcp://30.11.240.29:61616";
    private static String brokerURL = "tcp://30.39.45.94:61616";
    //connection�Ĺ���
    private static ConnectionFactory factory;
    //���Ӷ���
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
     * ���㷢��
     * @param queueName
     * @param message
     * @throws Exception
     */
    public static void sendSingleMsg(String queueName , Object message) throws Exception{
        Session session = null;
        MessageProducer producer = null;

        try{

            //����һ��session
            //��һ������:�Ƿ�֧���������Ϊtrue�������Եڶ�����������jms����������ΪSESSION_TRANSACTED
            //��һ������Ϊfalseʱ���ڶ���������ֵ��ΪSession.AUTO_ACKNOWLEDGE��Session.CLIENT_ACKNOWLEDGE��DUPS_OK_ACKNOWLEDGE����һ����
            //Session.AUTO_ACKNOWLEDGEΪ�Զ�ȷ�ϣ��ͻ��˷��ͺͽ�����Ϣ����Ҫ������Ĺ����������ǽ��ն˷����쳣��Ҳ�ᱻ�����������ͳɹ���
            //Session.CLIENT_ACKNOWLEDGEΪ�ͻ���ȷ�ϡ��ͻ��˽��յ���Ϣ�󣬱������javax.jms.Message��acknowledge������jms�������Żᵱ�����ͳɹ�����ɾ����Ϣ��
            //DUPS_OK_ACKNOWLEDGE��������ȷ��ģʽ��һ�����շ�Ӧ�ó���ķ������ôӴ�����Ϣ�����أ��Ự����ͻ�ȷ����Ϣ�Ľ��գ����������ظ�ȷ�ϡ�
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //����һ�������Ŀ�ĵأ���ʵ��һ�¾�֪���ˣ�activemq������ͬʱֻ����һ�����аɣ��������������һ����Ϊ"text-msg"�Ķ��У�����Ự���ᵽ������У���Ȼ�����������в����ڣ����ᱻ����
            Destination destination = session.createQueue(queueName);

            //��session�У���ȡһ����Ϣ������
            producer = session.createProducer(destination);

            //���������ߵ�ģʽ�������ֿ�ѡ
            //DeliveryMode.PERSISTENT ��activemq�رյ�ʱ�򣬶������ݽ��ᱻ����
            //DeliveryMode.NON_PERSISTENT ��activemq�رյ�ʱ�򣬶�����������ݽ��ᱻ���
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            //����һ����Ϣ����Ȼ����Ϣ�������кܶ࣬�����֣��ֽڣ������,����ͨ��session.create..��������������
            ObjectMessage objectMessage = session.createObjectMessage((Serializable)message);
            producer.send(objectMessage);

            System.out.println("��Ե㷢����Ϣ:" + message.toString());

        }  finally {
            //���������ߵĶ���ر��ˣ�����������Ŷ
            if(producer != null) {
                producer.close();
            }
            if(session != null) {
                session.close();
            }
        }
    }

    /**
     * ��ӵ�Ե������
     * @param queueName
     * @param listener
     * @throws JMSException
     */
    public static void addSingleListener(String queueName , MessageListener listener) throws JMSException {
        Session session = null;

        try {
            //����һ��session
            //��һ������:�Ƿ�֧���������Ϊtrue�������Եڶ�����������jms����������ΪSESSION_TRANSACTED
            //�ڶ�������Ϊfalseʱ��paramB��ֵ��ΪSession.AUTO_ACKNOWLEDGE��Session.CLIENT_ACKNOWLEDGE��DUPS_OK_ACKNOWLEDGE����һ����
            //Session.AUTO_ACKNOWLEDGEΪ�Զ�ȷ�ϣ��ͻ��˷��ͺͽ�����Ϣ����Ҫ������Ĺ����������ǽ��ն˷����쳣��Ҳ�ᱻ�����������ͳɹ���
            //Session.CLIENT_ACKNOWLEDGEΪ�ͻ���ȷ�ϡ��ͻ��˽��յ���Ϣ�󣬱������javax.jms.Message��acknowledge������jms�������Żᵱ�����ͳɹ�����ɾ����Ϣ��
            //DUPS_OK_ACKNOWLEDGE��������ȷ��ģʽ��һ�����շ�Ӧ�ó���ķ������ôӴ�����Ϣ�����أ��Ự����ͻ�ȷ����Ϣ�Ľ��գ����������ظ�ȷ�ϡ�
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //����һ�������Ŀ�ĵأ���ʵ��һ�¾�֪���ˣ�activemq������ͬʱֻ����һ�����аɣ��������������һ����Ϊ"text-msg"�Ķ��У�����Ự���ᵽ������У���Ȼ�����������в����ڣ����ᱻ����
            Destination destination = session.createQueue(queueName);

            //����session������һ�������߶���
            MessageConsumer consumer = session.createConsumer(destination);

            //ʵ��һ����Ϣ�ļ�����
            //ʵ��������������Ժ�ֻҪ����Ϣ���ͻ�ͨ��������������յ�
            consumer.setMessageListener(listener);


        }finally {
            if(session != null) {
                // session ���ܹ�
                //session.close();
            }
        }
    }

    /**
     * �㲥����
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
            System.out.println("�㲥������Ϣ��" + message.toString());
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
                // session ���ܹ�
                //session.close();
            }
        }
    }
}
