package com.redis.test.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisConsumer {

    public void onMessage(String msg, String pattern) {
        log.info("topic {} , received {} ", pattern, msg);
    }

}
