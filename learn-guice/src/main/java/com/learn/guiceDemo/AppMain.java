package com.learn.guiceDemo;

import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.learn.guiceDemo.modules.HelloServiceModule;
import com.learn.guiceDemo.service.HelloService;
import com.learn.guiceDemo.service.impl.HelloServiceImpl;

import java.util.concurrent.*;

public class AppMain {

    @Inject
    private HelloService helloService;

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(5), new ThreadPoolExecutor.CallerRunsPolicy());


    public void hi(){
        helloService.hello("nick");
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new HelloServiceModule(new HelloServiceImpl()));
        AppMain appMain = injector.getInstance(AppMain.class);
        appMain.hi();
        System.out.println("核心线程空闲超时是否关闭:" + executor.allowsCoreThreadTimeOut());//核心线程空闲超时是否关闭:false
        for (int i = 0; i < 15; i++) {
            MyRunnable myTask = new MyRunnable(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行完的任务数目：" + executor.getCompletedTaskCount());
        }
        executor.shutdown();//手动关闭线程池
        while (!executor.isTerminated()) {
        }
        if (executor.isTerminated()) {
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行完的任务数目：" + executor.getCompletedTaskCount());
        }
    }

    static class MyRunnable implements Runnable{

        private int k;
        public MyRunnable(int k){
            this.k = k;
        }
        public void run() {
            try {
                System.out.println("thread name:" + Thread.currentThread().getName()+"_"+k);
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

