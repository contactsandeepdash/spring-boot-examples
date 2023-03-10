package com.dash.camelmicroservice.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

// @Component
public class RestAPIConsumerRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration().host("localhost").port(7116);

        from("timer:rest-api-consumer?period=10000")
        .setHeader("from", () -> "EUR")
        .setHeader("to", () -> "INR")
        .log("${body}")
        .to("rest:get:/currency-exchange/from/{from}/to/{to}")
        .log("${body}");
    }
    
}
