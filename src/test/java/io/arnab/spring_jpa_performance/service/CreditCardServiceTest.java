package io.arnab.spring_jpa_performance.service;

import io.arnab.spring_jpa_performance.ApplicationTestConfiguration;
import io.arnab.spring_jpa_performance.domain.CreditCardRepository;
import io.arnab.spring_jpa_performance.infrastructure.CreditCardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(ApplicationTestConfiguration.class)
public class CreditCardServiceTest {

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Test
    void testDeactivation() {
        creditCardService.deactivate("CC01");
    }

    @Test
    void testGetCreditCards() {
        var response = creditCardService.getCreditCards(List.of("CC01"));
        assertThat(response.size()).isEqualTo(1);
        assertThat(response).isEqualTo(List.of(new CreditCardDTO("CC01", "AMEX")));
    }

    @Test
    void testGetCreditCardsDTO() {
        var response = creditCardRepository.findDtoById("CC01").orElseThrow();
        assertThat(response).isEqualTo(new CreditCardDTO("CC01", "AMEX"));
    }

    @Test
    void testGetCreditCardsDTOGeneric() {
        var response = creditCardRepository.findById("CC01", CreditCardDTO.class);
        assertThat(response).isEqualTo(new CreditCardDTO("CC01", "AMEX"));
    }
}
