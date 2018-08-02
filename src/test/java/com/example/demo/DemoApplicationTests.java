package com.example.demo;

import com.example.demo.mq.ActiveManager;
import com.example.demo.service.HandleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {

    @Autowired
    private ActiveManager activeManager;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private HandleService handleService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void dosth() throws InterruptedException {
        System.out.println(1);
        taskExecutor.execute(() -> {
            try {
                Thread.sleep(1000*5l);
                System.out.println("今天是个好日子！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(2);
        Thread.sleep(10000l);

    }

    @Test
    public void producer() throws InterruptedException {
        Destination destination = new ActiveMQQueue("beyondLiQueueTest");
        //传入队列以及值
        activeManager.send(destination, "success");
        Thread.sleep(1000*30);
    }

    @Test
    public void logTest(){
        log.trace("======trace");
        log.debug("======debug");
        log.info("======info");
        log.warn("======warn");
        log.error("======error");

        String name = "Aub";
        String message = "3Q";
        String[] fruits = { "apple", "banana" };

        // logback提供的可以使用变量的打印方式，结果为"Hello,Aub!"
        log.info("Hello,{}!", name);

        // 可以有多个参数,结果为“Hello,Aub! 3Q!”
        log.info("Hello,{}!   {}!", name, message);

        // 可以传入一个数组，结果为"Fruit:  apple,banana"
        log.info("Fruit:  {},{}", fruits);
    }

    @Test
    public void testAop(){
        handleService.handle();
    }

    @Test
    public void redisTest(){
        stringRedisTemplate.opsForValue().set("article:1",System.currentTimeMillis()+"",1,TimeUnit.MINUTES);
        String s = stringRedisTemplate.opsForValue().get("article:1");
        System.out.println(s);
    }

}
