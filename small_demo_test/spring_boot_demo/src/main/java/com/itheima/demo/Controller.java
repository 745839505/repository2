package com.itheima.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private Environment env;

    @RequestMapping("/info")
    public String info(){

        return "去到了spring-boot的"+env.getProperty("url");
    }


    @RequestMapping("/info2")
    public String info2(){

        return "去到了spring-boot的"+env.getProperty("url");
    }

}
