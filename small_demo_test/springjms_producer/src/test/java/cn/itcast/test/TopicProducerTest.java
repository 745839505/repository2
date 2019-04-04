package cn.itcast.test;

import cn.itcast.demo.TopicProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-activemq-producer.xml")
public class TopicProducerTest {

    @Autowired
    private TopicProducer topicProducer;

    @Test
    public void testTopicProducer(){

        //System.out.println(topicProducer);

        topicProducer.sendTextMessage("producer-jms-发布订阅 :艾吉奥处决了西泽尔和罗德里戈两个波吉亚家族的人");
    }
}
