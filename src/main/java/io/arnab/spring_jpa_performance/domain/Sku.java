package io.arnab.spring_jpa_performance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sku")
public class Sku {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "title")
    private String title;

    @CreationTimestamp
    @JsonProperty("created_at")
    @JsonFormat(timezone = "Asia/Kolkata")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonProperty("updated_at")
    @JsonFormat(timezone = "Asia/Kolkata")
    private LocalDateTime updatedAt;

    public Sku() {}

    public Sku(String title) {
        this.title = title;
    }
}
