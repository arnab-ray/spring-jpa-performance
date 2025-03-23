package io.arnab.spring_jpa_performance.service;

import io.arnab.spring_jpa_performance.domain.*;
import io.arnab.spring_jpa_performance.infrastructure.ReservationDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ReservationService {
    private final ReservationOrderRepository reservationOrderRepository;
    private final CustomerRepository customerRepository;
    private final SkuRepository skuRepository;

    public ReservationService(
            ReservationOrderRepository reservationOrderRepository,
            CustomerRepository customerRepository,
            SkuRepository skuRepository) {
        this.reservationOrderRepository = reservationOrderRepository;
        this.customerRepository = customerRepository;
        this.skuRepository = skuRepository;
    }

    // A case to highlight projections in spring data
    public List<ReservationDTO> getReservations(String customerId) {
        var reservations = reservationOrderRepository.findByCustomerId(customerId);
        return reservations.stream()
                .map(it -> new ReservationDTO(it.getId(), it.getState()))
                .toList();
    }

    public void createReservation(String customerId, Map<String, Integer> skuQuantity) {
        System.out.println(skuRepository.findAll());
        var customer = customerRepository.findById(customerId).orElseThrow();

        var reservationItems = skuQuantity.entrySet()
                .stream()
                .map(e -> {
                    var sku = skuRepository.findByTitle(e.getKey()).orElseThrow();
                    return new ReservationItem(sku, e.getValue());
                }).toList();
        var reservation = new ReservationOrder("RS09", customer, reservationItems);
        reservationOrderRepository.save(reservation);
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
