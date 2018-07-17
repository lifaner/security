package com.example.demo;

import com.example.demo.mq.ActiveManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {

    @Autowired
    private ActiveManager activeManager;

    @Test
    public void producer() {
        Destination destination = new ActiveMQQueue("beyondLiQueueTest");
        //传入队列以及值
        activeManager.send(destination, "success");
    }


    @Test
    public void consumer(){
        class ConsumerClass{
            @JmsListener(destination = "beyondLiQueueTest")
            public void con(String info){
                log.info(info);
                System.out.println(info);
            }
        }
    }

}
