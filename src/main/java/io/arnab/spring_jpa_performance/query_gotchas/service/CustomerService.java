package io.arnab.spring_jpa_performance.query_gotchas.service;

import io.arnab.spring_jpa_performance.shared_kernel.CustomerRepository;
import io.arnab.spring_jpa_performance.query_gotchas.domain.CreditCardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // org.hibernate.LazyInitializationException: failed to lazily initialize a collection of
    // role: io.arnab.spring_jpa_performance.shared_kernel.Customer.creditCards: could not initialize proxy - no Session.
    //
    // Session is a persistence context that represents a conversation between an application and the database.
    // Lazy Loading means that the object won’t be loaded to the Session context until it is accessed in code.
    // Hibernate creates a dynamic Proxy Object subclass that will hit the database only when we first use the
    // object.
    // This error occurs when we try to fetch a lazy-loaded object from the database by using a proxy object, but the Hibernate session is already closed.
    public List<CreditCardDTO> getCreditCardsOfCustomer(String customerId) {
        var customer = customerRepository.findByIdOrElseThrow(customerId);
        return customer.getCreditCards()
                .stream()
                .map(it -> new CreditCardDTO(it.getId(), it.getIssuer()))
                .toList();
    }
}
