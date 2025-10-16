package com.raven.central_pos.product.mapper;

import com.raven.central_pos.category.mapper.CategoryMapper;
import com.raven.central_pos.category.model.Category;
import com.raven.central_pos.product.contracts.dtos.ProductDto;
import com.raven.central_pos.product.models.Product;
import com.raven.central_pos.store.mappers.StoreMapper;
import com.raven.central_pos.store.model.Store;

public class ProductMapper {
    public static ProductDto toDto(Product product){

        return ProductDto.builder()
                .id(product.getId())
                .category(CategoryMapper.toDto(product.getCategory()))
                .storeId(product.getStore()!=null? product.getStore().getId():null)
                .name(product.getName())
                .brand(product.getBrand())
                .color(product.getColor())
                .imageUrl(product.getImageUrl())
                .description(product.getDescription())
                .sku(product.getSku())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                        .build()
                ;

    }
    public static Product toEntity(ProductDto productDto, Store store, Category category){
        return Product.builder()
                .name(productDto.getName())
                .brand(productDto.getBrand())
                .color(productDto.getColor())
                .store(store)
                .category(category)

                .description(productDto.getDescription())
                .sku(productDto.getSku())
                .mrp(productDto.getMrp())
                .sellingPrice(productDto.getSellingPrice())
                .build();
    }
}
