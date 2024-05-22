package org.feignwebfluxdocker.exchange.api.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExchangeRateResponse {
    private Double conversion_rate;
    private Double conversion_result;
}
