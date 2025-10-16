package com.raven.central_pos.product.services;

import com.raven.central_pos.product.contracts.dtos.ProductDto;
import com.raven.central_pos.user.model.User;

import java.util.List;

public interface IProductService {
    ProductDto createProduct(ProductDto productDto, User user) throws Exception;
    List<ProductDto> getProductByStoreId(Long storeId);
    List<ProductDto> searchProductByKeyword(Long storeId, String keyword);
    ProductDto updateProduct(Long id, ProductDto productDto,User user) throws Exception;
    void deleteProduct(Long id, User user) throws Exception;




}
