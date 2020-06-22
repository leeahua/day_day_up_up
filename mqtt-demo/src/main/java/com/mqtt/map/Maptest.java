package com.mqtt.map;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.concurrent.ConcurrentHashMap;

public class Maptest {
    public static final Logger log = LoggerFactory.getLogger(Maptest.class);

    public static ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>(16);


    public static void main(String[] args) {
        String str=" 1.2 >=1.2";
        Expression compiledExp = AviatorEvaluator.compile(str);
        System.out.println((boolean)compiledExp.execute());

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine se = manager.getEngineByName("js");
        try {
            System.out.println((boolean)se.eval(str));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
