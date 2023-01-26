package com.dash.rabbitmqproducerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dash.rabbitmqproducerservice.service.RabbitMQProducer;

@RestController
@RequestMapping("/api/v1")
public class MessagingContrlloler {

    @Autowired
    RabbitMQProducer producer;


    public void MessageController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    // http://localhost:8080/api/v1/publish?message=hello
    // @PostMapping("/publish")
    // public ResponseEntity<String> sendMessage(@RequestBody String message){
    //     producer.sendMessage(message);
    //     return ResponseEntity.ok("Message sent to RabbitMQ ...");
    // }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }

    // @GetMapping("/consume")
    // public ResponseEntity<String> receiveMessage() {
    //     consumer.listen(null);
    //     return ResponseEntity.ok("message received");
    // }
}
