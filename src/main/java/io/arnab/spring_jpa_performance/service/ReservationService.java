package io.arnab.spring_jpa_performance.service;

import io.arnab.spring_jpa_performance.domain.*;
import io.arnab.spring_jpa_performance.infrastructure.ReservationDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationOrderRepository reservationOrderRepository;

    public ReservationService(ReservationOrderRepository reservationOrderRepository) {
        this.reservationOrderRepository = reservationOrderRepository;
    }

    // A case to highlight EAGER/LAZY evaluation trade-offs
    @Transactional
    public void cancel(String reservationId) {
        // By default, many-to-one relation is set to EAGER evaluation, so when this query
        // executes, it fetches customer information which is not required. So we should
        // set fetch type to LAZY to prevent unnecessary query.
        var reservation = reservationOrderRepository.findById(reservationId).orElseThrow();
        reservation.cancel();
        // This save call is unnecessary
        reservationOrderRepository.save(reservation);
    }

    // A case to highlight projections in spring data
    public List<ReservationDTO> getReservations(String customerId) {
        var reservations = reservationOrderRepository.findByCustomerId(customerId);
        return reservations.stream()
                .map(it -> new ReservationDTO(it.getId(), it.getState()))
                .toList();
    }

    @Transactional
    public List<ReservationDTO> reservedSkus(String customerId) {
        var reservations = reservationOrderRepository.findByCustomerId(customerId);

        return reservations.stream()
                .flatMap(order ->
                        order.getReservationItems()
                                .stream()
                                .map(it ->
                                        new ReservationDTO(order.getId(), order.getState())))
                .toList();
    }
}
