package com.mqtt.loadbalance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
* 负载-轮询算法
*
* @author liyaohua
* @date 11:01 2020/6/3
* **/
public class RoundStrategy implements BalanceHandler{

    public static void main(String[] args) {
        int k =0;
        BalanceHandler balanceHandler = new RoundStrategy();
        while (k <=20) {

            System.out.println(balanceHandler.findServer());
            k++;
        }

    }

    private int pos = 0;

    @Override
    public String findServer() {
        Set<String> servers = Server.servers.keySet();
        List<String> list = new ArrayList<>();
        for (String key: servers) {
            list.add(key);
        }
        int tag = pos;
        pos ++;
        if(pos == servers.size()){
            pos = 0;
        }
        return list.get(tag);
    }
}
