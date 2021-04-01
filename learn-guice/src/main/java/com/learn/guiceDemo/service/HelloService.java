package com.learn.guiceDemo.service;

/**
 * 业务接口
 * @author lyh
 * @date 2021/3/18 11:09
 * **/
public interface HelloService {

    final String name = "nimo";

    /**
    * 测试injext
    * @param nimo
    * @return void
    * @author liyaohua
    * @date 11:09 2021/3/18
    * **/
    void hello(String nimo);
}
