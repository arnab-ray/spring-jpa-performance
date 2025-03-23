package io.arnab.spring_jpa_performance.service;

import io.arnab.spring_jpa_performance.ApplicationTestConfiguration;
import io.arnab.spring_jpa_performance.domain.ReservationOrderRepository;
import io.arnab.spring_jpa_performance.domain.ReservationState;
import io.arnab.spring_jpa_performance.infrastructure.ReservationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(ApplicationTestConfiguration.class)
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationOrderRepository reservationOrderRepository;

    @Test
    void testReservation() {
        reservationService.createReservation("USR01", Map.of("sku-3", 2, "sku-4", 1));
    }

    @Test
    void testReservationCancellation() {
        reservationService.cancel("RS01");
        var updatedReservation = reservationOrderRepository.findById("RS01").orElseThrow();
        assertThat(updatedReservation.getState()).isEqualTo(ReservationState.CANCELLED);
    }

    @Test
    void testGetReservations() {
        var response = reservationService.getReservations("USR01");
        assertThat(response.size()).isEqualTo(1);
        assertThat(response.getFirst().state()).isEqualTo(ReservationState.CREATED);
    }

    @Test
    void testReservedSkus() {
        List<ReservationDTO> reservations = reservationService.reservedSkus("USR01");
        assertThat(reservations).isNotEmpty();
        assertThat(reservations.size()).isEqualTo(10);
    }
}
