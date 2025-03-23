package io.arnab.spring_jpa_performance.service;

import io.arnab.spring_jpa_performance.domain.CreditCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    // A case to highlight EAGER/LAZY evaluation trade-offs
    @Transactional
    public void deactivate(String creditCardNumber) {
        // By default, many-to-one relation is set to EAGER evaluation, so when this query
        // executes, it fetches customer information which is not required. So we should
        // set fetch type to LAZY to prevent unnecessary query.
        var creditCard = creditCardRepository.findById(creditCardNumber).orElseThrow();
        creditCard.deactivate();
        // This save call is unnecessary
        creditCardRepository.save(creditCard);
    }
}
