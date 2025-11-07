package com.raven.central_pos.store.services.impl;

import com.raven.central_pos.core.exceptions.UserException;
import com.raven.central_pos.store.contracts.dto.StoreDto;
import com.raven.central_pos.store.domain.StoreStatus;
import com.raven.central_pos.store.mappers.StoreMapper;
import com.raven.central_pos.store.model.Store;
import com.raven.central_pos.store.model.StoreContact;
import com.raven.central_pos.store.repository.StoreRepository;
import com.raven.central_pos.store.services.IStoreService;
import com.raven.central_pos.user.model.User;
import com.raven.central_pos.user.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements IStoreService {
    private final StoreRepository storeRepository;
    private final IUserService userService;

    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {
        Store store = StoreMapper.toEntity(storeDto, user);
        return StoreMapper.toDto(storeRepository.save(store));
    }

    @Override
    public StoreDto getStoreById(Long id) throws Exception {
        Store store = storeRepository.findById(id).orElseThrow(
                ()->new Exception("store not found...")
        );
        return StoreMapper.toDto(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
     List<Store> stores =  storeRepository.findAll();
     return stores.stream().map(StoreMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User admin = userService.getCurrentUser();
        return storeRepository.findByStoreAdminId(admin.getId());
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws Exception {
        User currentUser = userService.getCurrentUser();
        Store existingStore = storeRepository.findByStoreAdminId(currentUser.getId() );
        if(existingStore==null){
       throw new Exception("store not found");
   }
        existingStore.setStoreName(storeDto.getStoreName());
        existingStore.setDescription(storeDto.getDescription());
        existingStore.setUpdatedAt(LocalDateTime.now());

        if (storeDto.getStoreType()!=null){
            existingStore.setStoreType(storeDto.getStoreType());
        }
        if(storeDto.getStoreContact()!=null){
            StoreContact contact = StoreContact.builder()
                    .address(storeDto.getStoreContact().getAddress())
                    .phone(storeDto.getStoreContact().getPhone())
                    .email(storeDto.getStoreContact().getEmail())
                    .build();

            existingStore.setStoreContact(contact);
        }
        Store updatedStore = storeRepository.save(existingStore);
        return StoreMapper.toDto(updatedStore);
    }

    @Override
    public void deleteStore(Long id) throws UserException {
        Store store = getStoreByAdmin();
        storeRepository.delete(store);

    }

    @Override
    public StoreDto getStoreByEmployee() throws UserException {
        User currentUser = userService.getCurrentUser();
        if (currentUser==null){
            throw new UserException("sorry, you do not have permission to access this store");
        }

        return StoreMapper.toDto(currentUser.getStore());
    }

    @Override
    public StoreDto moderateStore(Long id, StoreStatus status) throws Exception {
        Store store = storeRepository.findById(id).orElseThrow(
                ()->new Exception("store not found...")
        );
        store.setStoreStatus(status);
        Store updatedStore=storeRepository.save(store);
        return StoreMapper.toDto(updatedStore);
    }
}
