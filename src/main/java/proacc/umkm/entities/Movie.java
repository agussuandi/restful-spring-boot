package proacc.umkm.entities;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now().toString();
    }

    @PreUpdate void preUpdate() {
        updatedAt = LocalDateTime.now().toString();
    }
}