package com.example.demo.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * Create by lifan.
 * Date: 2018/7/17.
 * Time: 16:54.
 */
@Component
@Slf4j
public class ActiveManager {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    /**
     * @param data
     * @desc 即时发送
     */
    public void send(Destination destination, String data) {
        this.jmsMessagingTemplate.convertAndSend(destination, data);
    }

    @JmsListener(destination = "beyondLiQueueTest")
    public void con(String info){
        log.info(info);
        System.out.println(info);
    }


}