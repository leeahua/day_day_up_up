package com.mqtt.disruptor.officedemo;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/***
*  事件发布者
*
* @author liyaohua
* @date 17:59 2020/6/15
* **/
public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer){
        long sequence = ringBuffer.next();
        try{
            LongEvent event = ringBuffer.get(sequence);
            event.setValue(byteBuffer.getLong(0));
        }finally {
            ringBuffer.publish(sequence);
        }

    }
}
