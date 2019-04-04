package com.itheima.demo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
public class QueueController {

    @Autowired
    private JmsMessagingTemplate template;

    @RequestMapping("/send")
    public void send(String text){

        template.convertAndSend("itheima",text);
    }


    @RequestMapping("/sendMap")
    public void sendMap(){

        Map map = new HashMap<>();

        map.put("phone","18381121942");
        map.put("signName","黑马");
        map.put("templateCode","SMS_149422874");
        map.put("templateParam","{\"number\":\"chenWei\", \"code\":\"123\"}");

        template.convertAndSend("activemq_sms",map);
    }
}
