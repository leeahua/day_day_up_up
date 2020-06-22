package com.mqtt.loadbalance;

/**
* 负载均衡-随机选择策略
*
* @author liyaohua
* @date 11:49 2020/6/3
* **/
public class RandomStrategy implements BalanceHandler {
    @Override
    public String findServer() {
        return null;
    }
}
