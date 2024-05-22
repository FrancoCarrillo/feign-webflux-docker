package org.feignwebfluxdocker.exchange.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRequest {
    private String originCurrency;
    private Double originAmount;
    private String destinationCurrency;
    private Long userId;
}
