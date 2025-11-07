package com.raven.central_pos.branch.mapper;

import com.raven.central_pos.branch.contracts.BranchDto;
import com.raven.central_pos.branch.model.Branch;
import com.raven.central_pos.store.mappers.StoreMapper;
import com.raven.central_pos.store.model.Store;
import com.raven.central_pos.user.model.User;

import java.time.LocalDateTime;

public class BranchMapper {
    public static BranchDto toDto(Branch branch){
        return BranchDto.builder()
                .id(branch.getId())
                .name(branch.getName())
                .email(branch.getEmail())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .storeId(branch.getStore()!=null?branch.getStore().getId():null)
                .store(StoreMapper.toDto(branch.getStore()))
                .workingDays(branch.getWorkingDays())
                .openTime(branch.getOpenTime())
                .closeTime(branch.getCloseTime())
                .createdAt(branch.getCreatedAt())
                .updatedAt(branch.getUpdatedAt())
                .build();
    }

    public static Branch toEntity(BranchDto branchDto, Store store){
        return Branch.builder()
                .name(branchDto.getName())
                .address(branchDto.getAddress())
                .email(branchDto.getEmail())
                .phone(branchDto.getPhone())
                .store(store)
                //.manager(branchManager)
                .workingDays(branchDto.getWorkingDays())
                .openTime(branchDto.getOpenTime())
                .closeTime(branchDto.getCloseTime())
                .createdAt(LocalDateTime.now())
                //.updatedAt(LocalDateTime.now())

                .build();
    }
}
