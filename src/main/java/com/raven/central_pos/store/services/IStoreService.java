package com.raven.central_pos.store.services;

import com.raven.central_pos.core.exceptions.UserException;
import com.raven.central_pos.store.contracts.dto.StoreDto;
import com.raven.central_pos.store.domain.StoreStatus;
import com.raven.central_pos.store.model.Store;
import com.raven.central_pos.user.model.User;

import java.util.List;

public interface IStoreService {
    StoreDto createStore(StoreDto storeDto, User user);
    StoreDto getStoreById(Long id) throws Exception;
    List<StoreDto> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDto updateStore(Long id, StoreDto storeDto) throws Exception;
    void deleteStore(Long id) throws UserException;
    StoreDto getStoreByEmployee() throws UserException;
    StoreDto moderateStore(Long id, StoreStatus status) throws Exception;
}
