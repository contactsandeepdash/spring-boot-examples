package com.dash.camelmicroserviceconsumer.router;

import java.math.BigDecimal;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dash.camelmicroserviceconsumer.bean.CurrencyExchange;

import lombok.extern.slf4j.Slf4j;

// @Component
public class KafkaReceiverJSONRouter extends RouteBuilder{

    @Autowired
    MycurrencyExchangeProcessor mycurrencyExchangeProcessor;

    @Autowired
    MycurrencyExchangeTransformer mycurrencyExchangeTransformer;

    @Override
    public void configure() throws Exception {

        log.info("File = {}", getClass().getName());

        // read the JSON message from the queue and unmarshall to JSON
        from("kafka:my-json-kafka-topic")
        .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
        .log("${body}")
        // .bean(mycurrencyExchangeProcessor)
        // .bean(mycurrencyExchangeTransformer)
        .to("log:received message from my-json-kafka-topic");
        
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
