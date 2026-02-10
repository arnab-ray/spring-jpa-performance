package io.arnab.spring_jpa_performance.connection.infrastructure;

import io.arnab.spring_jpa_performance.connection.domain.CreateCustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConnectionBehaviourTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testConnectionHoldDuringViewRendering() {
        var response = testRestTemplate
                .postForEntity(
                        "/customer/v1",
                        new CreateCustomerDTO("Gabbar", "Singh", "9008796768"),
                        Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testConnectionHoldPriorToDBOperations() {
        var response = testRestTemplate
                .postForEntity(
                        "/customer/v2",
                        new CreateCustomerDTO("Gabbar", "Singh", "9008796768"),
                        Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testConnectionHoldPostDBOperations() {
        var response = testRestTemplate
                .postForEntity(
                        "/customer/v3",
                        new CreateCustomerDTO("Gabbar", "Singh", "9008796768"),
                        Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
