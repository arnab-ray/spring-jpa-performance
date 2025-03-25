package io.arnab.spring_jpa_performance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "mobile_number"))
    })
    private MobileNumber mobileNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<CreditCard> creditCards;

    @CreationTimestamp
    @JsonProperty("created_at")
    @JsonFormat(timezone = "Asia/Kolkata")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonProperty("updated_at")
    @JsonFormat(timezone = "Asia/Kolkata")
    private LocalDateTime updatedAt;

    public Customer() {}

    public Customer(String id, String firstName, String lastName, MobileNumber mobileNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
    }
}
