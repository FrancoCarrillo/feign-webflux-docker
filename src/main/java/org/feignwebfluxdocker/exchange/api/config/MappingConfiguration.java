package org.feignwebfluxdocker.exchange.api.config;

import org.feignwebfluxdocker.exchange.api.mapping.ExchangeMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("exchangeMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public ExchangeMapper exchangeMapper() {
        return new ExchangeMapper();
    }
}
