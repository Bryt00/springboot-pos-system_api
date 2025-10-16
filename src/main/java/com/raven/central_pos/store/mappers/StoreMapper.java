package com.raven.central_pos.store.mappers;

import com.raven.central_pos.store.contracts.dto.StoreDto;
import com.raven.central_pos.store.model.Store;
import com.raven.central_pos.user.contracts.dto.UserDto;
import com.raven.central_pos.user.mapper.UserMapper;
import com.raven.central_pos.user.model.User;

import java.time.LocalDateTime;

public class StoreMapper {
    public static StoreDto toDto(Store store){
        StoreDto storeDto = new StoreDto();
        storeDto.setId(store.getId() );
        storeDto.setStoreName(store.getStoreName());
        storeDto.setStoreType(store.getStoreType());
        storeDto.setStoreAdmin(UserMapper.toDTO(store.getStoreAdmin()));
        storeDto.setDescription(store.getDescription());
        storeDto.setStoreContact(store.getStoreContact());
        storeDto.setStoreStatus(store.getStoreStatus());
        storeDto.setCreatedAt(store.getCreatedAt());
        storeDto.setUpdatedAt(store.getUpdatedAt());

        return storeDto;

    }
    public static Store toEntity(StoreDto storeDto, User storeAdmin){
        Store store = new Store();
        store.setStoreName(storeDto.getStoreName());
        store.setStoreType(storeDto.getStoreType());
        store.setStoreAdmin(storeAdmin);
        store.setDescription(storeDto.getDescription());
        store.setStoreContact(storeDto.getStoreContact());
        store.setStoreStatus(storeDto.getStoreStatus());
        store.setCreatedAt(LocalDateTime.now());
        store.setUpdatedAt(LocalDateTime.now());


        return store;
    }
}
