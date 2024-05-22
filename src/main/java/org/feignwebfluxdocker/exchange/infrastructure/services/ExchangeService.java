package org.feignwebfluxdocker.exchange.infrastructure.services;

import lombok.RequiredArgsConstructor;
import org.feignwebfluxdocker.exchange.api.config.ExchangeFeignClient;
import org.feignwebfluxdocker.exchange.api.mapping.ExchangeMapper;
import org.feignwebfluxdocker.exchange.api.model.ExchangeRateResponse;
import org.feignwebfluxdocker.exchange.api.model.ExchangeRequest;
import org.feignwebfluxdocker.exchange.api.model.ExchangeResponse;
import org.feignwebfluxdocker.exchange.core.entities.Exchange;
import org.feignwebfluxdocker.exchange.core.repositories.ExchangeRepository;
import org.feignwebfluxdocker.exchange.infrastructure.interfaces.IExchangeService;
import org.feignwebfluxdocker.security.core.repositories.UserAccountRepository;
import org.feignwebfluxdocker.shared.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeService implements IExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final UserAccountRepository userAccountRepository;
    private final ExchangeMapper exchangeMapper;
    private final ExchangeFeignClient exchangeFeignClient;

    @Override
    public Mono<List<ExchangeResponse>> getAll() {
        return exchangeRepository.findAll()
                .collectList()
                .map(exchangeMapper::modelToListResponse);
    }

    @Override
    public Mono<ExchangeResponse> create(ExchangeRequest request) {

        return userAccountRepository.findById(request.getUserId())
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "User not found")))
                .flatMap(userAccount -> {

                    ExchangeRateResponse exchangeRateResponse =  exchangeFeignClient.getExchangeRate(request.getOriginAmount(), request.getOriginCurrency(), request.getDestinationCurrency());

                    Exchange exchange = exchangeMapper.responseToModel(request);
                    exchange.setDestinationAmount(exchangeRateResponse.getConversion_result());

                    return exchangeRepository.save(exchange)
                            .map(exchangeMapper::modelToResponse);
                }
        );
    }
}
