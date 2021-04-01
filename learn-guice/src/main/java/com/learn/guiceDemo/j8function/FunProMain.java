package com.learn.guiceDemo.j8function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunProMain {



    /**
    *
     * 修改业务值
    * @param value
    * @return void
    * @author liyaohua
    * @date 15:05 2021/3/22
    * **/
    public static int updateValue(int value){
        return ++value;
    }

    public static int update2Value(int value, Function<String, Integer> fun1){
        return fun1.apply("2");
    }

    public static String update3Value(String name, CountFunction fun1){
        return fun1.apply(name);
    }

    public static void main(String[] args) {
        int k = 1 ;
        int m = update2Value(k, new Function<String, Integer>() {
            @Override
            public Integer apply(String value1) {
                return Integer.valueOf(value1);
            }
        });
        System.out.println(m);
        System.out.println(update3Value("nick", new CountFunction()));
        Consumer<String>  consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("accept, " + s);
            }
        };
        consumer.accept("name");
        List<Integer> list = Arrays.asList(1, 2,3,4,5);
        Consumer<Integer> consumer1 = value -> System.out.println(value);
        list.stream().forEach(consumer1);
        list.stream().forEach(System.out:: print);
        HelloJ8 helloJ8 = new HelloJ8() {
            @Override
            public String Hello(String name) {
                return "j8";
            }
        };
        HelloJ8 helloJ81 = new HelloJ8Impl();
        System.out.println(helloJ81.Hello("nimo"));

        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer i) {
                return i == 0;
            }
        };
        System.out.println(predicate.test(0));
        checkKey(1, predicate);
    }

    public static void checkKey(Integer k, Predicate<Integer> predicate){
        if(predicate.test(k)){
            System.out.println("ok");
            return;
        }
        System.out.println("false");

    }
}
