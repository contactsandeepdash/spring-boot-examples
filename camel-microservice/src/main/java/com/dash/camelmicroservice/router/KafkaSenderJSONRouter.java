package com.dash.camelmicroservice.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

// @Component
public class KafkaSenderJSONRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("file:/Users/sandeepdash/Desktop/source/json")
        .log("${body}")
        .to("kafka:my-json-kafka-topic");

    }

}
