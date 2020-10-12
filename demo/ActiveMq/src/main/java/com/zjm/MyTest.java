package com.zjm;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * @author:�ھ�
 * @date:2017/9/3 20:03
 */
public class MyTest {

    public static void main(String[] args) throws Exception {

        // ���ӵ�Ե������
        ActiveMqClient.addSingleListener("MyQueue" , new MessageListener() {
            public void onMessage(Message message) {
                try {
                    //��ȡ�����յ�����
                    String text = ((ObjectMessage)message).getObject().toString();
                    System.out.println("��Ե������Ϣ��" + text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        // ���͵�Ե���Ϣ
        ActiveMqClient.sendSingleMsg("MyQueue" , "hello");

        // ���ӹ㲥������
        ActiveMqClient.addBroadcastListener("MyTopic" , new MessageListener() {
            public void onMessage(Message message) {
                try {
                    //��ȡ�����յ�����
                    String text = ((ObjectMessage)message).getObject().toString();
                    System.out.println("�㲥1������Ϣ��" + text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        // ���ӹ㲥������
        ActiveMqClient.addBroadcastListener("MyTopic" , new MessageListener() {
            public void onMessage(Message message) {
                try {
                    //��ȡ�����յ�����
                    String text = ((ObjectMessage)message).getObject().toString();
                    System.out.println("�㲥2������Ϣ��" + text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });


        // ���͹㲥��Ϣ
        ActiveMqClient.sendBroadcastMsg("MyTopic" , "hello");
    }
}
