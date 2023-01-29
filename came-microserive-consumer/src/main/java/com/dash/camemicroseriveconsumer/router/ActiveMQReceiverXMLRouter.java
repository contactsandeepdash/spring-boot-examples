package com.dash.camemicroseriveconsumer.router;

import java.math.BigDecimal;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dash.camemicroseriveconsumer.bean.CurrencyExchange;

import lombok.extern.slf4j.Slf4j;

// @Component
public class ActiveMQReceiverXMLRouter extends RouteBuilder{

    @Autowired
    MycurrencyExchangeProcessor mycurrencyExchangeProcessor;

    @Autowired
    MycurrencyExchangeTransformer mycurrencyExchangeTransformer;

    @Override
    public void configure() throws Exception {

        log.info("File = {}", getClass().getName());

        // read the JSON message from the queue and unmarshall to JSON
        from("activemq:spring_boot_activemq_queue")
        .unmarshal().jacksonXml(CurrencyExchange.class)
        .bean(mycurrencyExchangeProcessor)
        .bean(mycurrencyExchangeTransformer)
        .log("${body}")
        .to("log:received message from active mq");
        
    }
}

@Component
@Slf4j
class MycurrencyExchangeProcessor {
    public void processMessage(CurrencyExchange currencyExchange) {
        log.info("id = {}, from = {}, to = {}, conversionMultiple = {}", currencyExchange.getId(),
                currencyExchange.getFrom(), currencyExchange.getTo(), currencyExchange.getConversionMultiple());
    }
}

@Component
@Slf4j
class MycurrencyExchangeTransformer {
    public CurrencyExchange processMessage(CurrencyExchange currencyExchange) {
        currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));

        log.info("id = {}, from = {}, to = {}, conversionMultiple = {}", currencyExchange.getId(),
                currencyExchange.getFrom(), currencyExchange.getTo(), currencyExchange.getConversionMultiple());

        return currencyExchange;
    }
}