package com.dash.camemicroseriveconsumer.controller;

import java.math.BigDecimal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dash.camemicroseriveconsumer.bean.CurrencyExchange;

@RestController
public class CurrencyExchangeController {

    @GetMapping(value = "/currency-exchange/from/{from}/to/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CurrencyExchange findConversionValue(
            @PathVariable String from,
            @PathVariable String to) {

        return new CurrencyExchange(10000L, "USD", "INR", BigDecimal.TEN);
    }
}
