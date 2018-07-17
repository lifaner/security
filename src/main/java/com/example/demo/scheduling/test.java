package com.example.demo.scheduling;

import ch.qos.logback.core.util.FixedDelay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Operation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;

/**
 * Create by lifan.
 * Date: 2018/7/4.
 * Time: 17:28.
 */
@Slf4j
public class test {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

//    @Scheduled(fixedDelay = 1000*10)
    public void sendMessage(){
        System.out.println("这是定时任务！");
    }

}
