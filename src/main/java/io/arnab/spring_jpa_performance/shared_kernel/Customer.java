package io.arnab.spring_jpa_performance.shared_kernel;

import io.arnab.spring_jpa_performance.query_gotchas.domain.CreditCard;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
public class Customer implements Serializable {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "mobile_number"))
    })
    private MobileNumber mobileNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<CreditCard> creditCards;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected Customer() {}

    public Customer(String id, String firstName, String lastName, MobileNumber mobileNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
    }

    public void updateCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }
}
