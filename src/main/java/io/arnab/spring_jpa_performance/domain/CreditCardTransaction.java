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
@Table(name = "credit_card_transaction")
public class CreditCardTransaction {
    @Id
    private String id;

    private String creditCardId;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "amount"))})
    private Money amount;

    @JsonProperty("transaction_date")
    @JsonFormat(timezone = "Asia/Kolkata")
    private LocalDateTime transactionDate;

    @CreationTimestamp
    @JsonProperty("created_at")
    @JsonFormat(timezone = "Asia/Kolkata")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonProperty("updated_at")
    @JsonFormat(timezone = "Asia/Kolkata")
    private LocalDateTime updatedAt;
}
