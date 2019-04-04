package com.demo.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/***
 * 点对点消息生产者
 */
public class QueueProducer {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
        //创建连接
        Connection connection = factory.createConnection();
        connection.start();//开启
        //创建会话
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//1：事务是否提交;2:消息确认模式
        Queue queue = session.createQueue("test-queue");//创建队列对象
        MessageProducer producer = session.createProducer(queue);//创建消息生产者对象
        TextMessage textMessage = session.createTextMessage("欢迎来到里世界");//创建消息对象

        //发送消息
        producer.send(textMessage);

        //关闭资源
        connection.close();
        producer.close();
        session.close();
    }
}
