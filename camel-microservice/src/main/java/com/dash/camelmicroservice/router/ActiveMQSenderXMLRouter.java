package com.dash.camelmicroservice.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

// @Component
public class ActiveMQSenderXMLRouter extends RouteBuilder {

    @Override
        public void configure() throws Exception {

        // example 3 from a XML file to MQ
        from("file:/Users/sandeepdash/Desktop/source/xml")
        .log("${body}")
        .to("activemq:spring_boot_activemq_queue");
    }
    
}
