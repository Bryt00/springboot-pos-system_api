package com.raven.central_pos.product.controller;

import com.raven.central_pos.core.api_response.ApiResponse;
import com.raven.central_pos.product.contracts.dtos.ProductDto;
import com.raven.central_pos.product.services.IProductService;
import com.raven.central_pos.user.model.User;
import com.raven.central_pos.user.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final IProductService _productService;
    private final IUserService _userService;

    @PostMapping
   public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        User user = _userService.getUserFromJwtToken(jwt);
    return ResponseEntity.ok(_productService.createProduct(productDto, user));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductDto>> getByStoreId(@PathVariable Long storeId,
                                                         @RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(_productService.getProductByStoreId(storeId));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                                         @RequestBody ProductDto productDto,
                                                         @RequestHeader("Authorization") String jwt) throws Exception {

        User user = _userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(_productService.updateProduct(id,productDto,user));
    }

    @GetMapping("/store/{storeId}/search")
    public ResponseEntity<List<ProductDto>> searchByKeyword(
            @PathVariable Long storeId,
            @RequestParam String keyword,
            @RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(_productService.searchProductByKeyword(storeId, keyword));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id,

                                                     @RequestHeader("Authorization") String jwt) throws Exception {

        User user = _userService.getUserFromJwtToken(jwt);

        _productService.deleteProduct(id,user);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("product deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }
}
