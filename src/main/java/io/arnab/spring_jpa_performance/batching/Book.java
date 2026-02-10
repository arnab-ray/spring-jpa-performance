package io.arnab.spring_jpa_performance.batching;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
public class Book {

    @Id
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String authorId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Library library;

    private boolean available;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    protected Book() {}

    public Book(String title, Library library, String authorId) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.library = library;
        this.authorId = authorId;
        this.available = true;
    }

    public void borrowed() {
        this.available = false;
    }
}
