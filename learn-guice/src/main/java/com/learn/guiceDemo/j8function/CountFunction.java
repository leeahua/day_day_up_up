package com.learn.guiceDemo.j8function;

import java.util.function.Function;

public class CountFunction implements Function<String, String> {
    @Override
    public String apply(String s) {
        return "hello," + s;
    }
}
