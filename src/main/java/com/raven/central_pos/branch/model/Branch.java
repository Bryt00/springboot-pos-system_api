package com.raven.central_pos.branch.model;

import com.raven.central_pos.store.model.Store;
import com.raven.central_pos.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Branch {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private String email;

    @ElementCollection
    private List<String> workingDays;

    private LocalTime openTime;

    private LocalTime closeTime;

    @OneToOne(cascade = CascadeType.REMOVE)
    private User manager;

    @ManyToOne
    private Store store;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();

    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
