package com.mqtt.disruptor.officedemo;


/**
*  信号事件
*
* @author liyaohua
* @date 17:29 2020/6/15
* **/
public class LongEvent {

    private long value;

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue(){
        return this.value;
    }
}
