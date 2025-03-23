package io.arnab.spring_jpa_performance.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name = "reservation_item")
public class ReservationItem {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    private Sku sku;

    @ManyToOne
    private ReservationOrder reservationOrder;

    private Integer quantity;

    public ReservationItem() {}

    public ReservationItem(Sku sku, Integer quantity) {
        this.sku = sku;
        this.quantity = quantity;
    }
}
