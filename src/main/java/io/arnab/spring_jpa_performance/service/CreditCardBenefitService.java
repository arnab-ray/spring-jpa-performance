package io.arnab.spring_jpa_performance.service;

import io.arnab.spring_jpa_performance.domain.CardBenefit;
import io.arnab.spring_jpa_performance.domain.CardBenefitRepository;
import io.arnab.spring_jpa_performance.domain.CreditCardRepository;
import io.arnab.spring_jpa_performance.domain.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // @Transactional
    public void createBenefit(String customerId, String creditCardNumber) {
//        var customer = customerRepository.getReferenceById(customerId);
//        var creditCard = creditCardRepository.getReferenceById(creditCardNumber);

        var customer = customerRepository.findByIdOrElseThrow(customerId);
        var creditCard = creditCardRepository.findByIdOrElseThrow(creditCardNumber);

        var cardBenefit = new CardBenefit(customer, creditCard);
        cardBenefitRepository.save(cardBenefit);
    }
}
