package com.learn.jvm.aboutthread;

/**
 * 测试线程相关
 * @author lyh
 * @date 2021/3/17 20:04
 * **/
public class threadTest {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable(),"thread1");
        Thread thread2 = new Thread(new MyRunnable(),"thread2");
        thread1.start();
        thread2.start();

    }
}
