package io.arnab.spring_jpa_performance.query_gotchas.domain;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Currency;

@Embeddable
public record Money(Currency currency, BigDecimal amount) {
}
