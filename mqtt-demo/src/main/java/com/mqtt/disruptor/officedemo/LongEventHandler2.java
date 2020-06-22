package com.mqtt.disruptor.officedemo;


import com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*  事件消费端 即事件处理器
* @param
* @return
* @author liyaohua
* @date 17:43 2020/6/15
* **/
public class LongEventHandler2 implements EventHandler<LongEvent> {

    public static final Logger log = LoggerFactory.getLogger(LongEventHandler2.class);
    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("LongEventHandler222 Evet:{},"+longEvent+ "sequence:{},"+sequence+" endOfBatch:{}"+ endOfBatch);
    }
}
