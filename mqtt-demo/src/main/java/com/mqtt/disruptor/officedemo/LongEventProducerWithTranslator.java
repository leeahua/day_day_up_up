package com.mqtt.disruptor.officedemo;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
* 发布端转换器
 *
* @author liyaohua
* @date 17:44 2020/6/15
* **/
public class LongEventProducerWithTranslator {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = new
            EventTranslatorOneArg<LongEvent, ByteBuffer>() {
                @Override
                public void translateTo(LongEvent longEvent, long sequence, ByteBuffer byteBuffer) {
                    longEvent.setValue(byteBuffer.getLong(0));
                }
            };
    public void onData(ByteBuffer byteBuffer){
        ringBuffer.publishEvent(TRANSLATOR, byteBuffer);
    }
}
