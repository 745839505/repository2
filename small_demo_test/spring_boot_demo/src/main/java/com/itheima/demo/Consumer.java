package com.itheima.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Consumer {

    @JmsListener(destination = "itheima")
    public void readMessage(String message){

        System.out.println("获取到的消息是 :"+message);

    }

    @JmsListener(destination = "itheima_map")
    public void readMapMessage(Map message){

        System.out.println("获取到的消息是 :"+message);

    }
}
