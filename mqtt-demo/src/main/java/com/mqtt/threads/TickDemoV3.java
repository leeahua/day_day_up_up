package com.mqtt.threads;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;


public class TickDemoV3 {

    private static volatile int lastExeIndex = 0;
    private static volatile int num = 1;

    public static void main(String[] args) {
        Semaphore symbol = new Semaphore(1);
        Thread t1 = new Thread(new Task(symbol),"1");
        Thread t2 = new Thread(new Task(symbol),"2");
        Thread t3 = new Thread(new Task(symbol),"3");
        t1.start();
        t2.start();
        t3.start();
    }

    static class Task implements Runnable{

        private String name;
        private int order;
        private LinkedBlockingQueue myQueue;
        private Thread preThread;
        private int beginIndex;
        private Semaphore semaphore;

        public Task(String name,int order, LinkedBlockingQueue queue){
            this.name = name;
            this.order = order;
            this.myQueue = queue;
        }

        public Task(Semaphore beginIndex) {
            this.semaphore = beginIndex;
        }

        @Override
        public void run() {
            while (num <= 100){
                if(this.semaphore.tryAcquire()){
                    printNum();
                    this.semaphore.release();
                }else{
                    System.out.println("empty");
                }
            }
        }

        private void printNum() {
            if((num%3) == (Integer.valueOf(Thread.currentThread().getName())%3)){
                System.out.println(Thread.currentThread().getName()+" take:"+ num++);
            }
        }
    }

}
