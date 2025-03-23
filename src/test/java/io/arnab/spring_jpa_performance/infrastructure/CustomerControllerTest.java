package io.arnab.spring_jpa_performance.infrastructure;

import io.arnab.spring_jpa_performance.ApplicationTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(ApplicationTestConfiguration.class)
public class CustomerControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testCreateCustomer() {
        testRestTemplate
                .postForObject(
                        "/customer/v1",
                        new CreateCustomerDTO("Gabbar", "Singh", "9008796768"),
                        Void.class);
    }

    @Test
    void testValidateCreateCustomer() {
        testRestTemplate
                .postForObject(
                        "/customer/v2",
                        new CreateCustomerDTO("Gabbar", "Singh", "9008796768"),
                        Void.class);
    }
}
