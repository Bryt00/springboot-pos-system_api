package com.raven.central_pos.inventory.contracts;

import com.raven.central_pos.branch.contracts.BranchDto;
import com.raven.central_pos.branch.model.Branch;
import com.raven.central_pos.product.contracts.dtos.ProductDto;
import com.raven.central_pos.product.models.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InventoryDto {
    @Id
    private Long id;

    private BranchDto branch;

    private Long branchId;

    private ProductDto product;

    private Long productId;

    private Integer quantity;

    private LocalDateTime lastUpdate;
}
