package io.arnab.spring_jpa_performance.connection.infrastructure;

import io.arnab.spring_jpa_performance.shared_kernel.MobileNumber;
import io.arnab.spring_jpa_performance.connection.service.CustomerOnboardingService;
import io.arnab.spring_jpa_performance.query_gotchas.domain.CreateCustomerDTO;
import io.arnab.spring_jpa_performance.connection.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerOnboardingService customerOnboardingService;
    private final NotificationService notificationService;

    public CustomerController(
            CustomerOnboardingService customerOnboardingService,
            NotificationService notificationService) {
        this.customerOnboardingService = customerOnboardingService;
        this.notificationService = notificationService;
    }

    // Under default setting, the DB connection is not released as soon as
    // the transaction block is over. The connection remains open until the
    // http request is served. To disable this, we should check the open-in-view
    // flag.
    @PostMapping(value = "/v1")
    public void createAccount(@RequestBody CreateCustomerDTO createCustomerDTO) {
        var mobileNumber = new MobileNumber(createCustomerDTO.mobileNumber());
        customerOnboardingService.createAccount(createCustomerDTO);
        notificationService.notifyOnboarded(mobileNumber);
    }

    @PostMapping("/v2")
    public void validateAndGetAccount(@RequestBody CreateCustomerDTO createCustomerDTO) {
        customerOnboardingService.validateAndCreateAccount(createCustomerDTO);
    }

    @PostMapping("/v3")
    public void createAndNotify(@RequestBody CreateCustomerDTO createCustomerDTO) {
        customerOnboardingService.createAndNotify(createCustomerDTO);
    }
}
