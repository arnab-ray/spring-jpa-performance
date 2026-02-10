package io.arnab.spring_jpa_performance.batching;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext
@SpringBootTest
public class BatchingTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    @Test
    @Transactional
    void testForBatchInsert() {
        for (int i = 0; i < 10; i++) {
            Library library = new Library(UUID.randomUUID().toString());
            libraryRepository.save(library);
        }

        assertThat(libraryRepository.findAll()).hasSize(10);
    }

    @Test
    @Transactional
    void testForBatchInsertMultipleTable() {
        for (int i = 0; i < 10; i++) {
            Library library = new Library(UUID.randomUUID().toString());
            libraryRepository.save(library);

            Book book1 = new Book("Geetanjali", library, "Rabindranath Tagore");
            Book book2 = new Book("Dojakhnama", library, "Rabishankar Bal");
            bookRepository.save(book1);
            bookRepository.save(book2);
        }

        assertThat(libraryRepository.findAll()).hasSize(10);
        assertThat(bookRepository.findAll()).hasSize(20);
    }

    @Test
    @Transactional
    void testForBatchUpdate() {
        var libraries = insertMultipleLibrariesWithBooks();

        libraries.forEach(library -> updateBooks(library.getId()));

        assertThat(bookRepository.findAll().stream()
                .map(Book::isAvailable).filter(it -> !it)
                .count()).isEqualTo(20);
    }

    private List<Library> insertMultipleLibrariesWithBooks() {
        ArrayList<Library> libraries = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Library library = new Library(UUID.randomUUID().toString());

            Book book1 = new Book("Geetanjali", library, "Rabindranath Tagore");
            Book book2 = new Book("Dojakhnama", library, "Rabishankar Bal");

            var books = new ArrayList<>(List.of(book1, book2));
            library.setBooks(books);

            libraryRepository.save(library);
            bookRepository.save(book1);
            bookRepository.save(book2);

            libraries.add(library);
        }

        return libraries;
    }

    private void updateBooks(String libraryId) {
        var maybeLibrary = libraryRepository.findById(libraryId);

        if (maybeLibrary.isPresent()) {
            maybeLibrary.get().closed();
            var books = maybeLibrary.get().getBooks();
            System.out.println("Books: " + books.size());
            books.forEach(Book::borrowed);

            libraryRepository.save(maybeLibrary.get());
            bookRepository.saveAll(books);
        }
    }
}
