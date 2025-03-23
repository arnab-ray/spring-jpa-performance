package io.arnab.spring_jpa_performance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @ManyToOne
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

    public CreditCard(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.state = CardState.ACTIVATED;
    }
}
