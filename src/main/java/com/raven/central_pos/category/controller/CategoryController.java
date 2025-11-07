package com.raven.central_pos.category.controller;

import com.raven.central_pos.category.contracts.CategoryDto;
import com.raven.central_pos.category.services.ICategoryService;
import com.raven.central_pos.core.api_response.ApiResponse;
import com.raven.central_pos.user.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService _categoryService;
    private final IUserService _userService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategoryHandler(
            @RequestBody CategoryDto categoryDto) throws Exception {


    return ResponseEntity.ok(_categoryService.createCategory(categoryDto));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByStoreId(
            @PathVariable Long storeId) {

        return ResponseEntity.ok(_categoryService.getCategoriesByStore(storeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @RequestBody CategoryDto categoryDto,
            @PathVariable Long id) throws Exception {

        return ResponseEntity.ok(_categoryService.updateCategory(id,categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(
            @PathVariable Long id) throws Exception {

        _categoryService.deleteCategory(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("category deleted successfully");
        return ResponseEntity.ok(apiResponse);

    }
}
