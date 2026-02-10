package io.arnab.spring_jpa_performance.batching;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DirtiesContext
public class InvalidBatchingTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void testInvalidBatchUpdate() {
        for (int i = 0; i < 10; i++) {
            Author author = new Author("John", "Doe");
            entityManager.persist(author);
        }

        entityManager.flush();

        for (Author author : entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList()) {
            author.setFirstName("Jane");
            entityManager.persist(author);
        }

        entityManager.flush();
    }
}
