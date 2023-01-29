package com.dash.camemicroseriveconsumer.bean;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchange {
        private long id;
        private String from;
        private String to;
        private BigDecimal conversionMultiple;
    
}
