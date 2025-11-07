package com.raven.central_pos.user.mapper;

import com.raven.central_pos.user.contracts.dto.UserDto;
import com.raven.central_pos.user.model.User;

import java.time.LocalDateTime;

public class UserMapper {
    public static UserDto toDTO (User savedUser){
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setEmail(savedUser.getEmail());
        userDto.setFullName(savedUser.getFullName());
        userDto.setPhone(savedUser.getPhone());
        userDto.setRole(savedUser.getRole());
        userDto.setBranchId(savedUser.getBranch()!=null? savedUser.getBranch().getId():null);
        userDto.setStoreId(savedUser.getStore()!=null? savedUser.getStore().getId():null);
        userDto.setCreatedAt(savedUser.getCreatedAt());
        userDto.setUpdatedAt(savedUser.getUpdatedAt());
        userDto.setLastLoginAt(savedUser.getLastLoginAt());
        return userDto;
    }
    public static User toEntity(UserDto userDto){
        User createdUser = new User();
       // createduser.setId(userDto.getId());
        createdUser.setFullName(userDto.getFullName());
        createdUser.setEmail(userDto.getEmail());
        createdUser.setPhone(userDto.getPhone());
        createdUser.setPassword(userDto.getPassword());
        createdUser.setRole(userDto.getRole());
        createdUser.setCreatedAt(LocalDateTime.now());

        return createdUser;
    }

}
