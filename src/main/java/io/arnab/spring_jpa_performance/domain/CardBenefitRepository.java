package io.arnab.spring_jpa_performance.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardBenefitRepository extends JpaRepository<CardBenefit, String> {
    default CardBenefit findOrElseThrow(String id) {
        return findById(id).orElseThrow();
    }
}
