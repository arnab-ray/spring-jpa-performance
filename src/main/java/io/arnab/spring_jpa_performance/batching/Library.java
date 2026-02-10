package io.arnab.spring_jpa_performance.batching;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
public class Library {
    @Id
    private String id;

    @OneToMany(mappedBy = "library")
    private List<Book> books;

    private boolean isOpen;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    protected Library() {}

    public Library(String id) {
        this.id = id;
        this.isOpen = true;
    }

    public void closed() {
        this.isOpen = false;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
