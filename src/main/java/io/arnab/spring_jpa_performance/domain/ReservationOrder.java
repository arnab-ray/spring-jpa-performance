package io.arnab.spring_jpa_performance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "reservation_order")
public class ReservationOrder {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private ReservationState state;

    @OneToMany
    private List<ReservationItem> reservationItems;

    @CreationTimestamp
    @JsonProperty("created_at")
    @JsonFormat(timezone = "Asia/Kolkata")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonProperty("updated_at")
    @JsonFormat(timezone = "Asia/Kolkata")
    private LocalDateTime updatedAt;

    public ReservationOrder() {}

    public ReservationOrder(
            String id,
            Customer customer,
            List<ReservationItem> reservationItems) {
        this.id = id;
        this.customer = customer;
        this.reservationItems = reservationItems;
        this.state = ReservationState.CREATED;
    }

    public void cancel() {
        this.state = ReservationState.CANCELLED;
    }
}
