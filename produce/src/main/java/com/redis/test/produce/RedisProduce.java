package com.redis.test.produce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class RedisProduce {

    @Autowired
    private RedisTemplate redisTemplate;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private ChannelTopic topic = new ChannelTopic("/redis/pubsub");
    private ChannelTopic topic2 = new ChannelTopic("/redis/pub2");

    @Scheduled(initialDelay = 5000, fixedDelay = 1000)
    public void produce(){
        log.info("start produce!");
        redisTemplate.convertAndSend(topic.getTopic(), "hello->"+atomicInteger.addAndGet(1));
    }

}
