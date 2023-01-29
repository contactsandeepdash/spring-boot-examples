package com.dash.camelmicroservice.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

// @Component
public class ActiveMQSenderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // example 1 from timer to MQ
        // timer
        from("timer:activemq-timier?period=10000")
                .transform().constant("constant message for Active MQ")
                .log("${body}")
                .to("activemq:spring_boot_activemq_queue");
        // queue

    }

}
