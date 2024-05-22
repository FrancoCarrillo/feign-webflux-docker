package org.feignwebfluxdocker.exchange.api.routers;

import lombok.extern.slf4j.Slf4j;
import org.feignwebfluxdocker.exchange.api.handlers.ExchangeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@Slf4j
public class ExchangeRouter {

    private static final String EXCHANGE = "api/exchange";

    @Bean
    RouterFunction<ServerResponse> exchangeRtr(ExchangeHandler exchangeHandler) {
        return RouterFunctions.route()
                .GET(EXCHANGE, exchangeHandler::getAll)
                .POST(EXCHANGE, exchangeHandler::create)
                .build();
    }
}
