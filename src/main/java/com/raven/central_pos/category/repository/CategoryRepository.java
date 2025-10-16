package com.raven.central_pos.category.repository;

import com.raven.central_pos.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByStoreId(Long storeId);

    Category findByName(String name);
}
