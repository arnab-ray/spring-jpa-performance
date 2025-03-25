package io.arnab.spring_jpa_performance.domain;

import io.arnab.spring_jpa_performance.infrastructure.CreditCardDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, String> {
    default CreditCard findByIdOrElseThrow(String id) {
        return findById(id).orElseThrow();
    }

    Optional<CreditCardDTO> findDtoById(String id);

    // An example of dynamic projection. This is exposed by spring data.
    // This is an alternative to the bloatware that would happen if we write
    // method for all possible combination of fields in an entity.
    // The drawback is that we cannot write custom query.
    <T> T findById(String id, Class<T> clazz);
}
