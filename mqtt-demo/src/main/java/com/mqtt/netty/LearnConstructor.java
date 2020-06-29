package com.mqtt.netty;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * netty 学习的时候看到了这个构造器，记下啦
 * @author lyh
 * @date 2020-6-29 20:09:34
 */
public class LearnConstructor {

    private String name;

    public LearnConstructor(){

    }
    public LearnConstructor(String name){
        this.name = name;
    }

    public void Sayhello(){
        System.out.println("hello constructor"+this.name);
    }

    public static void main(String[] args) {
        LearnConstructor learnConstractor = new LearnConstructor("1111");

        try {
            // LearnConstructor.class.newInstance() 仅仅能构造无参数哦的实例
            // Constructor 可以构造任意参数的实例
            LearnConstructor.class.newInstance().Sayhello();
            Constructor constructor = learnConstractor.getClass().getConstructor(String.class);
            LearnConstructor newInstance = (LearnConstructor)constructor.newInstance("121212");
            newInstance.Sayhello();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
