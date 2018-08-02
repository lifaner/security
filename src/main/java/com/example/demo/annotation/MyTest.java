package com.example.demo.annotation;

/**
 * Create by lifan.
 * Date: 2018/7/18.
 * Time: 15:18.
 */
public class MyTest {

    @MyAnnotation(hello = "hello Beijing",world = "hello world")
    public void output(){
        System.out.println("output method is running!");
    }

}
