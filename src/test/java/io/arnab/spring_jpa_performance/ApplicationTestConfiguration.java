package io.arnab.spring_jpa_performance;

import io.arnab.spring_jpa_performance.domain.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@TestConfiguration(proxyBeanMethods = false)
public class ApplicationTestConfiguration {

    @Bean
    @ServiceConnection
    MySQLContainer<?> mySQLContainer() {
        return new MySQLContainer<>(DockerImageName.parse("mysql:8.0")).withReuse(true);
    }

    @Bean
    ApplicationRunner applicationRunner(
            CustomerRepository customerRepository, CreditCardRepository creditCardRepository) {
        return args -> {
            var customer = new Customer("USR01", "Soorma", "Bhopali", new MobileNumber("9028796769"));

            var creditCards = List.of(
                    new CreditCard("CC01", "AMEX", customer),
                    new CreditCard("CC02", "VISA", customer));

            customer.setCreditCards(creditCards);
            customerRepository.save(customer);
            creditCardRepository.saveAll(creditCards);
        };
    }

    public static void main(String[] args) {
        SpringApplication.from(Application::main)
                .with(ApplicationTestConfiguration.class)
                .run(args);
    }
}
