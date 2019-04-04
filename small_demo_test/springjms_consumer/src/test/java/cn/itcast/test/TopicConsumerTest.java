package cn.itcast.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-activemq-consumer-queue.xml")
public class TopicConsumerTest {

    @Test
    public void testTopicConsumer(){

        try {

            System.in.read();//等待键盘打印完毕
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
