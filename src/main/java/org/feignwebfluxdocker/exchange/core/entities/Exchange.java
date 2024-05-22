package org.feignwebfluxdocker.exchange.core.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("exchange")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exchange {
    @Id
    private Long id;
    private String originCurrency;
    private Double originAmount;
    private String destinationCurrency;
    private Double destinationAmount;
    private LocalDateTime createdDate;
    private Long userId;
}
