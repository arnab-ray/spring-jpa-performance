package io.arnab.spring_jpa_performance.service;

import io.arnab.spring_jpa_performance.ApplicationTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(ApplicationTestConfiguration.class)
public class CreditCardServiceTest {

    @Autowired
    private CreditCardService creditCardService;

    @Test
    void testDeactivation() {
        creditCardService.deactivate("CC01");
    }
}
