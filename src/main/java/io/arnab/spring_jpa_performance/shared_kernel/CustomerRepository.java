package io.arnab.spring_jpa_performance.shared_kernel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    default Customer findByIdOrElseThrow(String id) {
        return findById(id).orElseThrow();
    }

    Optional<Customer> findByMobileNumber(MobileNumber mobileNumber);
}
