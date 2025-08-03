package com.maia.vinicius.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(nullable = false, unique = true)
    private String username;

    @Setter
    private String password;

    @Getter @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Getter @Setter
    private String role = "USER";

    @Column(name = "created_at", updatable = false)
    @Getter
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Getter
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public User() {}

    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
