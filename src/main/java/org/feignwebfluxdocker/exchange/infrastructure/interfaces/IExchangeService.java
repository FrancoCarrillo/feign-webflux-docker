package org.feignwebfluxdocker.exchange.infrastructure.interfaces;

import org.feignwebfluxdocker.exchange.api.model.ExchangeRequest;
import org.feignwebfluxdocker.exchange.api.model.ExchangeResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IExchangeService {
    Mono<List<ExchangeResponse>> getAll();
    Mono<ExchangeResponse> create(ExchangeRequest request);
}
