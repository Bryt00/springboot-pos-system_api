package com.raven.central_pos.category.services.impl;

import com.raven.central_pos.category.contracts.CategoryDto;
import com.raven.central_pos.category.mapper.CategoryMapper;
import com.raven.central_pos.category.model.Category;
import com.raven.central_pos.category.repository.CategoryRepository;
import com.raven.central_pos.category.services.ICategoryService;
import com.raven.central_pos.core.exceptions.UserException;
import com.raven.central_pos.store.model.Store;
import com.raven.central_pos.store.repository.StoreRepository;
import com.raven.central_pos.user.domain.UserRole;
import com.raven.central_pos.user.model.User;
import com.raven.central_pos.user.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository _categoryRepository;
    private final IUserService _userService;
    private final StoreRepository _storeRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws Exception {
        //Category category = _categoryRepository.findByName(categoryDto.getName());
        User user = _userService.getCurrentUser();
        Store store = _storeRepository.findById(categoryDto.getStoreId()).orElseThrow(
                ()->new Exception("store not found")
        );

        Category category = Category.builder()
                .store(store)
                .name(categoryDto.getName())
                .build();

        checkAuthority(user,category.getStore());



        return CategoryMapper.toDto(_categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getCategoriesByStore(Long storeId) {
        List<Category> categories = _categoryRepository.findByStoreId(storeId);
        return categories.stream().map(CategoryMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws Exception {
       Category category = _categoryRepository.findById(id).orElseThrow(
               ()-> new Exception("category does not exist")
       );
       User user = _userService.getCurrentUser();

        checkAuthority(user, category.getStore());
       category.setName(categoryDto.getName());
       return CategoryMapper.toDto(_categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category = _categoryRepository.findById(id).orElseThrow(
                ()-> new Exception("category does not exist")
        );

        User user = _userService.getCurrentUser();

        checkAuthority(user, category.getStore());

        _categoryRepository.delete(category);

    }
    private void checkAuthority(User user, Store store) throws Exception {
        boolean isStoreAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = user.equals(store.getStoreAdmin());

        if(!(isStoreAdmin && isSameStore) && !isManager){
            throw new Exception("you don't have the permission to manage this category");
        }

    }
}
