package com.raven.central_pos.category.contracts;

import com.raven.central_pos.store.model.Store;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {

    @Id
    private Long id;

    private String name;

    private Long storeId;
    //private String storeId;
}
