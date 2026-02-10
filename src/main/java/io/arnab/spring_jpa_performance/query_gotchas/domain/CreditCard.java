package io.arnab.spring_jpa_performance.query_gotchas.domain;

import io.arnab.spring_jpa_performance.shared_kernel.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
public class CreditCard {
    @Id
    private String id;

    private String issuer;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private CardState state;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    public CreditCard() {}

    public void deactivate() {
        this.state = CardState.DEACTIVATED;
    }

    public CreditCard(String id, String issuer, Customer customer) {
        this.id = id;
        this.issuer = issuer;
        this.customer = customer;
        this.state = CardState.ACTIVATED;
    }
}
