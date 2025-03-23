package io.arnab.spring_jpa_performance;

import io.arnab.spring_jpa_performance.domain.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;
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
            CustomerRepository customerRepository,
            SkuRepository skuRepository,
            ReservationItemRepository reservationItemRepository,
            ReservationOrderRepository reservationOrderRepository) {
        return args -> {
            var customer = new Customer("USR01", "Soorma", "Bhopali", new MobileNumber("9028796769"));
            customerRepository.save(customer);

            List<ReservationItem> items = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                var sku = skuRepository.save(new Sku("sku-" + i));
                var reservationItem = reservationItemRepository.save(new ReservationItem(sku, 1));
                items.add(reservationItem);
            }
            var reservation = new ReservationOrder("RS01", customer, items);
            reservationOrderRepository.save(reservation);
        };
    }

    public static void main(String[] args) {
        SpringApplication.from(Application::main)
                .with(ApplicationTestConfiguration.class)
                .run(args);
    }
}
