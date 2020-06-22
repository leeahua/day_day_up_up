package com.redis.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RedisConsumerApplication {
    public static void main(String... args) {
        SpringApplication.run(RedisConsumerApplication.class, args);
    }
}
