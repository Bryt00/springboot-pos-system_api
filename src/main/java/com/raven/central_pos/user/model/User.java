package com.raven.central_pos.user.model;

import com.raven.central_pos.store.model.Store;
import com.raven.central_pos.user.domain.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique =true)
    @Email(message = "Email must be valid")
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;

    @ManyToOne
    private Store store;

    @Column(nullable=false)
    private UserRole role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastLoginAt;
}
