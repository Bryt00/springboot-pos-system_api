package com.raven.central_pos.branch.services.impl;

import com.raven.central_pos.branch.contracts.BranchDto;
import com.raven.central_pos.branch.mapper.BranchMapper;
import com.raven.central_pos.branch.model.Branch;
import com.raven.central_pos.branch.repository.BranchRepository;
import com.raven.central_pos.branch.services.IBranchService;
import com.raven.central_pos.core.exceptions.UserException;
import com.raven.central_pos.store.model.Store;
import com.raven.central_pos.store.repository.StoreRepository;
import com.raven.central_pos.user.model.User;
import com.raven.central_pos.user.services.IUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BranchServiceImpl implements IBranchService {
    private final BranchRepository _branchRepository;
    private final StoreRepository _storeRepository;
    private final IUserService _userService;
    @Override
    public BranchDto createBranch(BranchDto branchDto) throws UserException {
        User currentUser = _userService.getCurrentUser();
        Store store = _storeRepository.findByStoreAdminId(currentUser.getId());

        Branch branch = BranchMapper.toEntity(branchDto, store);
        Branch createdBranch = _branchRepository.save(branch);
        return BranchMapper.toDto(createdBranch);
    }

    @Override
    public BranchDto updateBranch(Long id, BranchDto branchDto) throws Exception {
        Branch existingBranch = _branchRepository.findById(id).orElseThrow(
                ()->new Exception("branch does not exist")
        );
        existingBranch.setName(branchDto.getName());
        existingBranch.setEmail(branchDto.getEmail());
        existingBranch.setPhone(branchDto.getPhone());
        existingBranch.setAddress(branchDto.getAddress());
        existingBranch.setWorkingDays(branchDto.getWorkingDays());
        existingBranch.setOpenTime(branchDto.getOpenTime());
        existingBranch.setCloseTime(branchDto.getCloseTime());
        existingBranch.setUpdatedAt(LocalDateTime.now());

        Branch updatedBranch = _branchRepository.save(existingBranch);
        return BranchMapper.toDto(updatedBranch);

    }

    @Override
    public void deleteBranch(Long id) throws Exception {
        Branch existingBranch = _branchRepository.findById(id).orElseThrow(
                ()->new Exception("branch does not exist")
        );
        _branchRepository.delete(existingBranch);
    }

    @Override
    public List<BranchDto> getAllBranchesByStoreId(Long storeId) {

               List<Branch> branchList= _branchRepository.findByStoreId(storeId);
            return   branchList.stream().map(BranchMapper::toDto)
                       .collect(Collectors.toList());

    }

    @Override
    public BranchDto getBranchById(Long id) throws Exception {
        Branch branch = _branchRepository.findById(id).orElseThrow(
                ()->new Exception("branch does not exist")
        );

        return BranchMapper.toDto(branch);
    }
}
