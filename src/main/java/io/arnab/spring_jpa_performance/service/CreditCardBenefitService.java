package io.arnab.spring_jpa_performance.service;

import io.arnab.spring_jpa_performance.domain.CardBenefit;
import io.arnab.spring_jpa_performance.domain.CardBenefitRepository;
import io.arnab.spring_jpa_performance.domain.CreditCardRepository;
import io.arnab.spring_jpa_performance.domain.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditCardBenefitService {
    private final CustomerRepository customerRepository;
    private final CreditCardRepository creditCardRepository;
    private final CardBenefitRepository cardBenefitRepository;

    public CreditCardBenefitService(
            CustomerRepository customerRepository,
            CreditCardRepository creditCardRepository,
            CardBenefitRepository cardBenefitRepository) {
        this.customerRepository = customerRepository;
        this.creditCardRepository = creditCardRepository;
        this.cardBenefitRepository = cardBenefitRepository;
    }

    // This is a case where we are fetching data which is not required. This happens
    // because hibernate session is not transactional and closes it after every step.
    // So during insert, it tries to validate if the entities exist. One way to fix this
    // is to use @Version. The other step would be to use @Transactional. But these won't
    // completely eliminate select calls. To address that we should use getReferenceById
    // which would mean that we only pass reference, i.e. foreign key constraints are
    // validatedFirst during insertion.
    public void createBenefit(String customerId, String creditCardNumber) {
        var customer = customerRepository.findByIdOrElseThrow(customerId);
        var creditCard = creditCardRepository.findByIdOrElseThrow(creditCardNumber);

        var cardBenefit = new CardBenefit(customer, creditCard);
        cardBenefitRepository.save(cardBenefit);
    }
}
