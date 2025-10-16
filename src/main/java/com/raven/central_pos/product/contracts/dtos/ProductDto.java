package com.raven.central_pos.product.contracts.dtos;

import com.raven.central_pos.category.contracts.CategoryDto;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductDto {

    @Id
    private Long id;

    private String name;
    private String sku;
    private String description;
    private Double mrp;
    private Double sellingPrice;
    private String brand;
    private String color;
    private String imageUrl;
    private CategoryDto category;
    private Long storeId;
    private Long categoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
