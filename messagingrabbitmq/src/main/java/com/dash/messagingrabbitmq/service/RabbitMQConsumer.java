package com.dash.messagingrabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitMQConsumer {

    // @RabbitListener(queues = {"${rabbitmq.queueName}"})
    public void listen(@Payload String message){
        log.info(String.format("Received message -> %s", message));
    }

    // public void listen(byte[] message) {
    //     String msg = new String(message);
    //     Notification not = new Gson().fromJson(msg, Notification.class);
    //     System.out.println("Received a new notification...");
    //     System.out.println(not.toString());
    // }
}
