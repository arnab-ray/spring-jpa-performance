package io.arnab.spring_jpa_performance.batching;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findByLibraryId(String libraryId);
}
