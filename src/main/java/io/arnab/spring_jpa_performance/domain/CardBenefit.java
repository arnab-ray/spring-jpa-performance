package io.arnab.spring_jpa_performance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "card_benefit")
public class CardBenefit {
    @Id
    @UuidGenerator
    private String id;

    @OneToOne
    private Customer customer;

    @OneToOne
    private CreditCard creditCard;

    @CreationTimestamp
    @JsonProperty("created_at")
    @JsonFormat(timezone = "Asia/Kolkata")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonProperty("updated_at")
    @JsonFormat(timezone = "Asia/Kolkata")
    private LocalDateTime updatedAt;

    private Integer point;

    public CardBenefit() {}

    public CardBenefit(Customer customer, CreditCard creditCard) {
        this.customer = customer;
        this.creditCard = creditCard;
        this.point = 1;
    }
}
