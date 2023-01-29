package com.dash.camelmicroservice.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

// @Component
public class KafkaSenderXMLRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("file:/Users/sandeepdash/Desktop/source/xml")
        .log("${body}")
        .to("kafka:my-xml-kafka-topic");

    }

}
