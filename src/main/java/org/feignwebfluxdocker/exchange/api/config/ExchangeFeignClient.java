package org.feignwebfluxdocker.exchange.api.config;

import org.feignwebfluxdocker.exchange.api.model.ExchangeRateResponse;
import org.feignwebfluxdocker.shared.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EXCHANGE-API", url = "${exchange.url}", configuration = FeignClientConfig.class )
public interface ExchangeFeignClient {


    @GetMapping(value = "/pair/{origin}/{destination}/{amount}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ExchangeRateResponse getExchangeRate(@PathVariable("amount") Double amount, @PathVariable("origin") String origin, @PathVariable("destination") String destination);
}
