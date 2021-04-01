package com.learn.jvm.aboutthread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRunnable implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(MyRunnable.class);

    public void run() {
        log.info("thread name:{}", Thread.currentThread().getName());
    }
}
