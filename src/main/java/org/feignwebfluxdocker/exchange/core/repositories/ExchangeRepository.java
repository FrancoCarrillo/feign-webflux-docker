package org.feignwebfluxdocker.exchange.core.repositories;

import org.feignwebfluxdocker.exchange.core.entities.Exchange;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface ExchangeRepository extends R2dbcRepository<Exchange, Long> {
}
