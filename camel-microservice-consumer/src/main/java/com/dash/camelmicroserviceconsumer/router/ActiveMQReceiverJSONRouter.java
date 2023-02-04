package com.dash.camelmicroserviceconsumer.router;

import java.math.BigDecimal;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dash.camelmicroserviceconsumer.bean.CurrencyExchange;

import lombok.extern.slf4j.Slf4j;

// @Component
public class ActiveMQReceiverJSONRouter extends RouteBuilder{

    @Autowired
    MycurrencyExchangeProcessor mycurrencyExchangeProcessor;

    @Autowired
    MycurrencyExchangeTransformer mycurrencyExchangeTransformer;

    @Override
    public void configure() throws Exception {

        log.info("File = {}", getClass().getName());

        // read the message directly from MQ
        // from("activemq:spring_boot_activemq_queue")
        // .to("log:received message from active mq");

        // read the JSON message from the queue and unmarshall to JSON
        from("activemq:spring_boot_activemq_queue")
        .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
        .bean(mycurrencyExchangeProcessor)
        .bean(mycurrencyExchangeTransformer)
        .to("log:received message from active mq");
        
    }
}

@Component
@Slf4j
class MycurrencyExchangeProcessor {
    public void processMessage(CurrencyExchange currencyExchange) {
        log.info("File = {}, id = {}, from = {}, to = {}, conversionMultiple = {}", getClass().getName(), currencyExchange.getId(),
                currencyExchange.getFrom(), currencyExchange.getTo(), currencyExchange.getConversionMultiple());
    }
}

@Component
@Slf4j
class MycurrencyExchangeTransformer {
    public CurrencyExchange processMessage(CurrencyExchange currencyExchange) {
        currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));

        log.info("File = {}, id = {}, from = {}, to = {}, conversionMultiple = {}", getClass().getName(), currencyExchange.getId(),
                currencyExchange.getFrom(), currencyExchange.getTo(), currencyExchange.getConversionMultiple());

        return currencyExchange;
    }
}
