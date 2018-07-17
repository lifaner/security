package com.example.demo.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * Create by lifan.
 * Date: 2018/7/17.
 * Time: 16:54.
 */
@Component
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


}