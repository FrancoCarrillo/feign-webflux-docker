package org.feignwebfluxdocker.exchange.api.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.feignwebfluxdocker.exchange.api.model.ExchangeRequest;
import org.feignwebfluxdocker.exchange.api.model.ExchangeResponse;
import org.feignwebfluxdocker.exchange.infrastructure.interfaces.IExchangeService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExchangeHandler {

    private final IExchangeService exchangeService;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return exchangeService.getAll()
                .flatMap(response -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response));
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<ExchangeRequest> exchangeRequest = request.bodyToMono(ExchangeRequest.class);

        return exchangeRequest
                .flatMap(res -> exchangeService.create(res)
                        .flatMap(response -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(response))
                );
    }
}
