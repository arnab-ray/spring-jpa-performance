package io.arnab.spring_jpa_performance.query_gotchas.service;

import io.arnab.spring_jpa_performance.query_gotchas.domain.CreditCard;
import io.arnab.spring_jpa_performance.query_gotchas.domain.CreditCardRepository;
import io.arnab.spring_jpa_performance.shared_kernel.Customer;
import io.arnab.spring_jpa_performance.shared_kernel.CustomerRepository;
import io.arnab.spring_jpa_performance.shared_kernel.MobileNumber;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@DirtiesContext
@SpringBootTest
public class BaseTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;

    @BeforeEach
    void  setUp() {
        customerRepository.deleteAll();
        creditCardRepository.deleteAll();

        var customer = new Customer("USR01", "Soorma", "Bhopali",
                new MobileNumber("9028796769"));

        var creditCards = new ArrayList<>(List.of(
                new CreditCard("CC01", "AMEX", customer),
                new CreditCard("CC02", "VISA", customer)));

        customer.setCreditCards(creditCards);
        customerRepository.save(customer);
        creditCards.forEach(creditCard -> creditCardRepository.save(creditCard));

        log.info("\n\n");
    }
}
