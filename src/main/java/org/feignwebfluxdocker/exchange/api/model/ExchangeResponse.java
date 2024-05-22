package org.feignwebfluxdocker.exchange.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeResponse {
    private Long id;
    private String originCurrency;
    private Double originAmount;
    private String destinationCurrency;
    private Double destinationAmount;
}
