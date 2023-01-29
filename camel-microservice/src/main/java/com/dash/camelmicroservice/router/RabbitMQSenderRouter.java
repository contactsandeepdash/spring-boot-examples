package com.dash.camelmicroservice.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

// @Component
public class RabbitMQSenderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // timer
        from("timer:rabbitmq-timier?period=10000")
        .transform().constant("constant message for Rabbit MQ")
        .log("${body}")
        .to("rabbitmq:spring_boot_rabbitmq_queue");
        // queue
    }
    
}
