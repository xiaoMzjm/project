package com.zjm;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * @author:黑绝
 * @date:2017/9/3 20:03
 */
public class MyTest {

    public static void main(String[] args) throws Exception {

        // 增加点对点监听器
        ActiveMqClient.addSingleListener("MyQueue" , new MessageListener() {
            public void onMessage(Message message) {
                try {
                    //获取到接收的数据
                    String text = ((ObjectMessage)message).getObject().toString();
                    System.out.println("点对点接收消息：" + text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        // 发送点对点消息
        ActiveMqClient.sendSingleMsg("MyQueue" , "hello");

        // 增加广播监听器
        ActiveMqClient.addBroadcastListener("MyTopic" , new MessageListener() {
            public void onMessage(Message message) {
                try {
                    //获取到接收的数据
                    String text = ((ObjectMessage)message).getObject().toString();
                    System.out.println("广播1接收消息：" + text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        // 增加广播监听器
        ActiveMqClient.addBroadcastListener("MyTopic" , new MessageListener() {
            public void onMessage(Message message) {
                try {
                    //获取到接收的数据
                    String text = ((ObjectMessage)message).getObject().toString();
                    System.out.println("广播2接收消息：" + text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });


        // 发送广播消息
        ActiveMqClient.sendBroadcastMsg("MyTopic" , "hello");
    }
}
