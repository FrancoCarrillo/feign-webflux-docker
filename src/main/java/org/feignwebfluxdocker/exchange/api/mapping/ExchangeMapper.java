package org.feignwebfluxdocker.exchange.api.mapping;

import org.feignwebfluxdocker.exchange.api.model.ExchangeRequest;
import org.feignwebfluxdocker.exchange.api.model.ExchangeResponse;
import org.feignwebfluxdocker.exchange.core.entities.Exchange;
import org.feignwebfluxdocker.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class ExchangeMapper {

    @Autowired
    private EnhancedModelMapper mapper;

    public ExchangeResponse modelToResponse(Exchange model) {
        return mapper.map(model, ExchangeResponse.class);
    }

    public List<ExchangeResponse> modelToListResponse(List<Exchange> models) {
        return mapper.mapList(models, ExchangeResponse.class);
    }

    public Exchange responseToModel(ExchangeRequest response) {
        Exchange exchange = mapper.map(response, Exchange.class);

        exchange.setCreatedDate(LocalDateTime.now());
        exchange.setDestinationAmount(1.1);

        return exchange;

    }

}
