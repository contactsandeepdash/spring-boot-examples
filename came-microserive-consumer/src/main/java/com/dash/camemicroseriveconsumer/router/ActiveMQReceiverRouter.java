package com.dash.camemicroseriveconsumer.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

// @Component
public class ActiveMQReceiverRouter extends RouteBuilder{

    @Autowired
    MycurrencyExchangeProcessor mycurrencyExchangeProcessor;

    @Autowired
    MycurrencyExchangeTransformer mycurrencyExchangeTransformer;

    @Override
    public void configure() throws Exception {

        // read the message directly from MQ
        from("activemq:spring_boot_activemq_queue")
        .to("log:received message from active mq");

        
        
    }
}