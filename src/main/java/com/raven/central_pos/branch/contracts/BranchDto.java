package com.raven.central_pos.branch.contracts;

import com.raven.central_pos.store.contracts.dto.StoreDto;
import com.raven.central_pos.store.model.Store;
import com.raven.central_pos.user.contracts.dto.UserDto;
import com.raven.central_pos.user.model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class BranchDto {
    @Id
    private Long id;

    private String name;

    private String address;

    private String phone;

    private String email;

    private List<String> workingDays;

    private LocalTime openTime;

    private LocalTime closeTime;

    private UserDto manager;

    private Long storeId;

    private StoreDto store;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
