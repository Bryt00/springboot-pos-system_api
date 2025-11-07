package com.raven.central_pos.inventory.mapper;

import com.raven.central_pos.branch.mapper.BranchMapper;
import com.raven.central_pos.branch.model.Branch;
import com.raven.central_pos.inventory.contracts.InventoryDto;
import com.raven.central_pos.inventory.model.Inventory;
import com.raven.central_pos.product.mapper.ProductMapper;
import com.raven.central_pos.product.models.Product;

import java.time.LocalDateTime;

public class InventoryMapper {
    public static InventoryDto toDto(Inventory inventory){
        return InventoryDto.builder()
                .id(inventory.getId())
                .branchId(inventory.getBranch().getId())
                .branch(BranchMapper.toDto(inventory.getBranch()))
                .productId(inventory.getProduct().getId())
                .product(ProductMapper.toDto(inventory.getProduct()))
                .quantity(inventory.getQuantity())
                .lastUpdate(inventory.getLastUpdate())
                .build();
    }
    public static Inventory toEntity(InventoryDto inventoryDto, Branch branch, Product product){
        return Inventory.builder()
                .branch(branch)
                .product(product)
                .quantity(inventoryDto.getQuantity())
                .build();
    }
}
