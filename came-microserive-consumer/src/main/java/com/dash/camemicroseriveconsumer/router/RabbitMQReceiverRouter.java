package com.dash.camemicroseriveconsumer.router;

import org.apache.camel.builder.RouteBuilder;

// @Component
public class RabbitMQReceiverRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("rabbitmq:spring_boot_rabbitmq_queue")
                .to("log:received message from rabbit mq");

    }

}


