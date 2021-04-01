package com.learn.guiceDemo.modules;

import com.google.inject.AbstractModule;
import com.learn.guiceDemo.service.HelloService;
import com.learn.guiceDemo.service.impl.HelloServiceImpl;

public class HelloServiceModule extends AbstractModule {

    private final HelloServiceImpl helloServiceImpl;

    public HelloServiceModule(HelloServiceImpl helloServiceImpl) {
        this.helloServiceImpl = helloServiceImpl;
    }

   protected void configure(){
        bind(HelloService.class).toInstance(helloServiceImpl);
   }
}
