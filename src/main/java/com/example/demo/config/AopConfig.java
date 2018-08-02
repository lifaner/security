package com.example.demo.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * Create by lifan.
 * Date: 2018/7/31.
 * Time: 13:51.
 */
@Aspect
@Configuration
public class AopConfig {

    @Pointcut("execution(* com.example.demo.service.HandleService.handle())")
    public void executeService(){}

    @Before(value = "executeService()")
    public void before(){
        System.out.println("切面before");
    }
}
