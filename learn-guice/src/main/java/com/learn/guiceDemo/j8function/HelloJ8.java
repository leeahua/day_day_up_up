package com.learn.guiceDemo.j8function;


public interface HelloJ8 {

    default String Hello(String name){
        return "hello," + name;
    }
}
