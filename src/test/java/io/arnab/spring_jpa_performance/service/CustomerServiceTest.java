package io.arnab.spring_jpa_performance.service;

import io.arnab.spring_jpa_performance.ApplicationTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(ApplicationTestConfiguration.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void testFetch() {
        var creditCards = customerService.customerCreditCards("USR01");
        assertThat(creditCards.size()).isEqualTo(2);
    }
}
