package io.arnab.spring_jpa_performance.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByMobileNumber(MobileNumber mobileNumber);

    Optional<Customer> findById(String id);
}
