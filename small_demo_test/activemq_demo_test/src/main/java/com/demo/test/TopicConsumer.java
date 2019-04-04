package com.demo.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicConsumer {

    /***
     * 点对点消息消费者
     */

        public static void main(String[] args) throws Exception{

            ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
            //创建连接
            Connection connection = factory.createConnection();
            connection.start();//开启
            //创建会话
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//1：事务是否提交;2:消息确认模式

            Topic topic = session.createTopic("topic_test");

            MessageConsumer consumer = session.createConsumer(topic);//消费者

            consumer.setMessageListener(new MessageListener() {//消费者设置监听者
                @Override
                public void onMessage(Message message) {

                    TextMessage textMessage = (TextMessage)message;//接收数据并强转类型

                    try {

                        System.out.println("提取到的消息 :"+textMessage.getText());
                    } catch (JMSException e) {
                    }
                }
            });

            System.in.read();//等待键盘打印(防止消息提取不到就关闭了)

            //关闭操作
            connection.close();
            consumer.close();
            session.close();
        }//psvm
}
