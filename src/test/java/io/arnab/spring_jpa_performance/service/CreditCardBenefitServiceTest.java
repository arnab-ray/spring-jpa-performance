package io.arnab.spring_jpa_performance.service;

import io.arnab.spring_jpa_performance.ApplicationTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(ApplicationTestConfiguration.class)
public class CreditCardBenefitServiceTest {
    @Autowired
    private CreditCardBenefitService creditCardBenefitService;

    @Test
    void testCreateBenefit() {
        creditCardBenefitService.createBenefit("USR01", "CC01");
    }
}
