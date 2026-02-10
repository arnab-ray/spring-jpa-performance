package io.arnab.spring_jpa_performance.batching;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, String> {
}
