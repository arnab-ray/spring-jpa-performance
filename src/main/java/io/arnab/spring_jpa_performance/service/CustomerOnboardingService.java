package io.arnab.spring_jpa_performance.service;

import io.arnab.spring_jpa_performance.domain.Customer;
import io.arnab.spring_jpa_performance.domain.CustomerRepository;
import io.arnab.spring_jpa_performance.domain.MobileNumber;
import io.arnab.spring_jpa_performance.infrastructure.CreateCustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.UUID;

@Service
public class CustomerOnboardingService {
    private final TransactionTemplate transactionTemplate;
    private final BlockedMobileService blockedMobileService;
    private final NotificationService notificationService;
    private final CustomerRepository customerRepository;

    public CustomerOnboardingService(
            PlatformTransactionManager transactionManager,
            BlockedMobileService blockedMobileService,
            NotificationService notificationService,
            CustomerRepository customerRepository) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.blockedMobileService = blockedMobileService;
        this.notificationService = notificationService;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void createAccount(CreateCustomerDTO createCustomerDTO) {
        var mobileNumber = new MobileNumber(createCustomerDTO.mobileNumber());
        var account = customerRepository.findByMobileNumber(mobileNumber);
        if (account.isEmpty()) {
            var customer = new Customer(UUID.randomUUID().toString(), createCustomerDTO.firstName(), createCustomerDTO.lastName(), mobileNumber);
            customerRepository.save(customer);
        }
    }

    @Transactional
    public void validateAndCreateAccount(CreateCustomerDTO createCustomerDTO) {
        var mobileNumber = new MobileNumber(createCustomerDTO.mobileNumber());
        // Under default setting, the DB connection is acquired since the method begins
        // with transaction annotation, so the JDBC driver can commit any time. Better
        // approach is to disable auto-commit and control when commit happens.
        var isBlocked = blockedMobileService.verify(mobileNumber);

        if (!isBlocked) {
            // Doing it via programmatic transaction block instead of transaction annotation
            // is better because we don't hold the connection for a longer duration.
            var account = customerRepository.findByMobileNumber(mobileNumber);
            if (account.isEmpty()) {
                var customer = new Customer(UUID.randomUUID().toString(), createCustomerDTO.firstName(), createCustomerDTO.lastName(), mobileNumber);
                customerRepository.save(customer);
            }
            notificationService.notifyOnboarded(mobileNumber);
        }
    }
}
