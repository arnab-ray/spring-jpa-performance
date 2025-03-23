package io.arnab.spring_jpa_performance.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, String> {
    default CreditCard findByIdOrElseThrow(String id) {
        return findById(id).orElseThrow();
    }
}
