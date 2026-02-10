package io.arnab.spring_jpa_performance.query_gotchas.domain;

import io.arnab.spring_jpa_performance.shared_kernel.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Getter
@Entity
public class CardBenefit {
    @Id
    @UuidGenerator
    private String id;

    @OneToOne
    private Customer customer;

    @OneToOne
    private CreditCard creditCard;

    private Integer point;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected CardBenefit() {}

    public CardBenefit(Customer customer, CreditCard creditCard, Integer point) {
        this.customer = customer;
        this.creditCard = creditCard;
        this.point = point;
    }

    public void updatePoint(Integer point) {
        this.point = this.point + point;
    }
}
