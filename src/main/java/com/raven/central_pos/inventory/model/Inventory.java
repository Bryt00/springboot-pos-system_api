package com.raven.central_pos.inventory.model;

import com.raven.central_pos.branch.model.Branch;
import com.raven.central_pos.product.models.Product;
import jakarta.persistence.*;
import jdk.jfr.Registered;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Branch branch;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    private LocalDateTime lastUpdate;
    @PrePersist
    @PreUpdate
    protected void onUpdate(){
        lastUpdate = LocalDateTime.now();
    }


}
