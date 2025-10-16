package com.raven.central_pos.product.services.impl;

import com.raven.central_pos.category.model.Category;
import com.raven.central_pos.category.repository.CategoryRepository;
import com.raven.central_pos.product.contracts.dtos.ProductDto;
import com.raven.central_pos.product.mapper.ProductMapper;
import com.raven.central_pos.product.models.Product;
import com.raven.central_pos.product.repository.ProductRepository;
import com.raven.central_pos.product.services.IProductService;
import com.raven.central_pos.store.model.Store;
import com.raven.central_pos.store.repository.StoreRepository;
import com.raven.central_pos.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository _productRepository;
    private final StoreRepository _storeRepository;
    private final CategoryRepository _categoryRepository;
    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws Exception {
        Store store = _storeRepository.findById(
                productDto.getStoreId()
        ).orElseThrow(
                ()->new Exception("store not found")
        );
        Category category = _categoryRepository.findById(productDto.getCategoryId())
                        .orElseThrow(
                                ()-> new Exception("category not found")
                        );
        Product product = ProductMapper.toEntity(productDto, store, category);
        Product savedProduct=_productRepository.save(product);
        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public List<ProductDto> getProductByStoreId(Long storeId) {
        List<Product> products = _productRepository.findByStoreId(storeId);
        return products
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors
                        .toList());
    }

    @Override
    public List<ProductDto> searchProductByKeyword(Long storeId, String keyword) {
        List<Product> products = _productRepository.searchByKeyword(storeId, keyword);
        return products
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors
                        .toList());
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception {
        Product existingProduct = _productRepository.findById(id).orElseThrow(
                ()-> new Exception("product not found")
        );

        existingProduct.setName(productDto.getName());
        existingProduct.setSku(productDto.getSku());
        existingProduct.setBrand(productDto.getBrand());
        existingProduct.setImageUrl(productDto.getImageUrl());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setMrp(productDto.getMrp());
        existingProduct.setSellingPrice(productDto.getSellingPrice());
        existingProduct.setUpdatedAt(LocalDateTime.now());

        if(productDto.getCategoryId()!=null){
            Category category = _categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                    ()->new Exception("category not found")
            );
            existingProduct.setCategory(category);
        }
       Product updatedProduct= _productRepository.save(existingProduct);
        return ProductMapper.toDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {
        Product product = _productRepository.findById(id).orElseThrow(
                ()->new Exception("product not found")
        );
        _productRepository.delete(product);

    }
}
