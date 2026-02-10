package io.arnab.spring_jpa_performance.query_gotchas.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardBenefitRepository extends JpaRepository<CardBenefit, String> {
    default CardBenefit findOrElseThrow(String id) {
        return findById(id).orElseThrow();
    }

    Optional<CardBenefit> findByCustomerId(String customerId);
}
