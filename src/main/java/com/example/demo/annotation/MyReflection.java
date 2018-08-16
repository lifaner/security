package com.example.demo.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by lifan.
 * Date: 2018/7/18.
 * Time: 15:20.
 */
public class MyReflection {

    public static void main(String[] args) throws NoSuchMethodException {
        //获取需要调用的类
         Class<MyTest> myTestClass = MyTest.class;
        //获得要调用的方法，output是要调用的方法名字，new Class[]{}为所需要的参数。空则不是这种
        Method method = myTestClass.getMethod("output", new Class[]{});
        //是否有类型是annotation类型的注解
        if(method.isAnnotationPresent(MyAnnotation.class)){
            Annotation annotation = method.getAnnotation(MyAnnotation.class);
            System.out.println(((MyAnnotation) annotation).hello());
            System.out.println(((MyAnnotation) annotation).world());
        }
        System.out.println("---------------------------------");
        // 获得所有注解。必须是runtime类型的
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations){
            // 遍历所有注解的名字
            System.out.println(annotation.annotationType().getName());
        }

    }

}
