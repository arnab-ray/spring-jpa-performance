package io.arnab.spring_jpa_performance.service;

import io.arnab.spring_jpa_performance.domain.CreditCardRepository;
import io.arnab.spring_jpa_performance.infrastructure.CreditCardDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        var creditCard = creditCardRepository.findByIdOrElseThrow(creditCardNumber);
        creditCard.deactivate();
        // This save call is unnecessary
        creditCardRepository.save(creditCard);
    }

    // A case to highlight projections in spring data
    public List<CreditCardDTO> getCreditCards(List<String> cards) {
        return cards.stream().map(it -> {
            var creditCard = creditCardRepository.findByIdOrElseThrow(it);
            return new CreditCardDTO(creditCard.getId(), creditCard.getIssuer());
        }).toList();

//        var creditCards = creditCardRepository.findByIdOrElseThrow(customerId);
//        return creditCards.stream()
//                .map(it -> new CreditCardDTO(it.getId()))
//                .toList();
    }
}
