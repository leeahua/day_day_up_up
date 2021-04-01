package com.learn.guiceDemo.service.impl;

import com.learn.guiceDemo.service.HelloService;

public class HelloServiceImpl implements HelloService {
    public void hello(String nimo) {
        System.out.println("hello," + HelloService.name);
    }
}
