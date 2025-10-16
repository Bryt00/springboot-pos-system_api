package com.raven.central_pos.store.contracts.dto;

import com.raven.central_pos.store.domain.StoreStatus;
import com.raven.central_pos.store.model.StoreContact;
import com.raven.central_pos.user.contracts.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class StoreDto {
    @Id
    private Long id;

    private String storeName;
    private UserDto storeAdmin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;
    private String storeType;
    private StoreStatus storeStatus;
    private StoreContact storeContact;
}
