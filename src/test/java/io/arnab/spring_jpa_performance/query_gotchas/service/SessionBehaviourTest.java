package io.arnab.spring_jpa_performance.query_gotchas.service;

import io.arnab.spring_jpa_performance.query_gotchas.domain.CardBenefitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionBehaviourTest extends BaseTest {

    @Autowired
    private CardBenefitRepository cardBenefitRepository;
    @Autowired
    private CreditCardBenefitService creditCardBenefitService;

    @Test
    void testCreateBenefit() {
        creditCardBenefitService.createBenefit("USR01", "CC01", 10);

        assertThat(cardBenefitRepository.findByCustomerId("USR01")).isPresent();
    }
}
