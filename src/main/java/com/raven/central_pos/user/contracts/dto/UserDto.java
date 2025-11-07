package com.raven.central_pos.user.contracts.dto;

import com.raven.central_pos.user.domain.UserRole;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    @Id
    private Long Id;

    private String fullName;
    private String email;
    private String password;
    private String phone;
    private UserRole role;
    private Long storeId;
    private Long branchId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;
}
