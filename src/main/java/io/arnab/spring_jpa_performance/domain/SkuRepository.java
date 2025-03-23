package io.arnab.spring_jpa_performance.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkuRepository extends JpaRepository<Sku, Long> {
    Optional<Sku> findByTitle(String title);
}
