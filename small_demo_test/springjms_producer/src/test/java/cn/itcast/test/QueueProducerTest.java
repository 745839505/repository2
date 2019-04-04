package cn.itcast.test;

import cn.itcast.demo.QueueProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-activemq-producer.xml")
public class QueueProducerTest {

    @Autowired
    private QueueProducer queueProducer;

    @Test
    public void send(){

        System.out.println(queueProducer);

        queueProducer.sendTextMessage("producer-jms-点对点：詹姆斯桑德兰打败了三角头和修女怪");
    }
}
