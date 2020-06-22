package com.mqtt.loadbalance;

import java.util.HashMap;
import java.util.Map;

public class Server {
    public static final Map<String,Integer> servers = new HashMap<>();

    static{
        for (int i = 0; i < 15; i++) {
            servers.put(i+":"+i+":"+i+":"+i, i);
        }
    }
}
