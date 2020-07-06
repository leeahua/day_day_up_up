package com.mqtt.threads;

import java.util.concurrent.LinkedBlockingQueue;

public class TickDemo {


    private static LinkedBlockingQueue queue = new LinkedBlockingQueue(100);

    private static volatile int lastExeIndex = 0;

    public static void main(String[] args) {

        for(int k =1 ; k<=100;k++){
            queue.add(k);
        }
        Thread t1 = new Thread(new Task("t1",1,queue));
        Thread t2 = new Thread(new Task("t2",2,queue));
        Thread t3 = new Thread(new Task("t3",3,queue));
        t1.start();
        t2.start();
        t3.start();
    }

    static class Task implements Runnable{

        private String name;
        private int order;
        private LinkedBlockingQueue myQueue;

        public Task(String name,int order, LinkedBlockingQueue queue){
            this.name = name;
            this.order = order;
            this.myQueue = queue;
        }
        @Override
        public void run() {
            while (!myQueue.isEmpty()){
                if(this.order - lastExeIndex == 1){
                    System.out.println(this.name+" take:"+ myQueue.poll());
                    lastExeIndex = (lastExeIndex+1) % 3;
                }
            }

        }
    }

}
