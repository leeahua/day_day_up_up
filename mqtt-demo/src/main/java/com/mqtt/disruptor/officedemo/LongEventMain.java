package com.mqtt.disruptor.officedemo;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

public class LongEventMain {

    public static void main(String[] args) throws InterruptedException {
        //the factory for the event
        LongEventFactory factory = new LongEventFactory();
        //specify the size of ring buffer, must be power of 2
        int bufferSize = 1024;
        //construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory,  bufferSize, DaemonThreadFactory.INSTANCE);
        //connect the Disruptor
        disruptor.handleEventsWith(new LongEventHandler());
        //start the Disruptor, starts all threads running
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for(long l = 0; true; l++){
            byteBuffer.putLong(0,l);
            ringBuffer.publishEvent(((longEvent, l1) -> longEvent.setValue(byteBuffer.getLong(0))));
            //producer.onData(byteBuffer);
            Thread.sleep(1000);
        }
    }
}
