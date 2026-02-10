package io.arnab.spring_jpa_performance.query_gotchas.service;

import io.arnab.spring_jpa_performance.query_gotchas.domain.CreditCard;
import io.arnab.spring_jpa_performance.query_gotchas.domain.CreditCardRepository;
import io.arnab.spring_jpa_performance.shared_kernel.Customer;
import io.arnab.spring_jpa_performance.shared_kernel.CustomerRepository;
import io.arnab.spring_jpa_performance.shared_kernel.MobileNumber;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@DirtiesContext
@SpringBootTest
public class BaseTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;

    @BeforeEach
    void  setUp() {
        var customer = new Customer("USR01", "Soorma", "Bhopali",
                new MobileNumber("9028796769"));

        var creditCards = List.of(
                new CreditCard("CC01", "AMEX", customer),
                new CreditCard("CC02", "VISA", customer));

        customer.updateCreditCards(creditCards);
        customerRepository.save(customer);
        creditCardRepository.saveAll(creditCards);
    }
}
