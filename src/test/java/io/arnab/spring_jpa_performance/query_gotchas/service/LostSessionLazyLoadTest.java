package io.arnab.spring_jpa_performance.query_gotchas.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class LostSessionLazyLoadTest extends BaseTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void testFetch() {
        var creditCards = customerService.getCreditCardsOfCustomer("USR01");
        assertThat(creditCards.size()).isEqualTo(2);
    }
}
