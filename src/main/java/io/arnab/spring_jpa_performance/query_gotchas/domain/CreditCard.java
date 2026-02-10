package io.arnab.spring_jpa_performance.query_gotchas.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.arnab.spring_jpa_performance.shared_kernel.Customer;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "credit_card")
public class CreditCard {
    @Id
    private String id;

    private String issuer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private CardState state;

    @CreationTimestamp
    @JsonProperty("created_at")
    @JsonFormat(timezone = "Asia/Kolkata")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonProperty("updated_at")
    @JsonFormat(timezone = "Asia/Kolkata")
    private LocalDateTime updatedAt;

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
