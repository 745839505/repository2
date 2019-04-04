package com.demo.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/***
 * 发布订阅模式生产者
 */
public class TopicProducer {

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
        //创建连接
        Connection connection = factory.createConnection();
        connection.start();//开启
        //创建会话
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//1：事务是否提交;2:消息确认模式

        Topic topic =session.createTopic("topic_test");

        MessageProducer producer = session.createProducer(topic);//创建消息生产者对象
        TextMessage textMessage = session.createTextMessage("欢迎来到了topic的三角头世界");//创建消息对象

        //发送消息
        producer.send(textMessage);

        //关闭资源
        connection.close();
        producer.close();
        session.close();
    }
}
