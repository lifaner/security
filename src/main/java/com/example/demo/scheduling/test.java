package com.example.demo.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Create by lifan.
 * Date: 2018/7/4.
 * Time: 17:28.
 */
@Component
public class test {

//    @Scheduled(fixedDelay = 1000*10)
    public void sendMessage(){
        System.out.println("这是定时任务！");
    }

}
