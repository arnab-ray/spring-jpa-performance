package io.arnab.spring_jpa_performance.domain;

import io.arnab.spring_jpa_performance.infrastructure.ReservationDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationOrderRepository extends JpaRepository<ReservationOrder, Long> {

    Optional<ReservationOrder> findById(String id);

    List<ReservationOrder> findByCustomerId(String customerId);

    // Sample to demonstrate that we can do projection in the JPA layer itself
    List<ReservationDTO> findDtoByCustomerId(String customerId);

    // An example of dynamic projection. This is exposed by spring data.
    // This is an alternative to the bloatware that would happen if we write
    // method for all possible combination of fields in an entity.
    // The drawback is that we cannot write custom query.
    <T> T findById(String id, Class<T> clazz);
}
