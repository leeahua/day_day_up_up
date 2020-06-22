package com.mqtt.disruptor.officedemo;

import com.lmax.disruptor.EventFactory;

/**
*  事件工厂
* @param
* @return
* @author liyaohua
* @date 17:30 2020/6/15
* **/
public class LongEventFactory implements EventFactory<LongEvent> {


    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
