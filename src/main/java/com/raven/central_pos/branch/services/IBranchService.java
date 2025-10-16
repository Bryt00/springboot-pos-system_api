package com.raven.central_pos.branch.services;

import com.raven.central_pos.branch.contracts.BranchDto;
import com.raven.central_pos.user.model.User;

import java.util.List;

public interface IBranchService {
    BranchDto createBranch(BranchDto branchDto, User user);
    BranchDto updateBranch(Long id, BranchDto branchDto, User user);
    BranchDto deleteBranch(Long id);
    List<BranchDto> getAllBranchesByStoreId(Long storeId);
    BranchDto getBranchId(Long id);
}
