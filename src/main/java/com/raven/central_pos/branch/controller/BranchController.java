package com.raven.central_pos.branch.controller;

import com.raven.central_pos.branch.contracts.BranchDto;
import com.raven.central_pos.branch.services.IBranchService;
import com.raven.central_pos.core.api_response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {
    private final IBranchService _branchService;

    @PostMapping
    public ResponseEntity<BranchDto> createBranchHandler(@RequestBody BranchDto branchDto) throws Exception {
        BranchDto newBranch = _branchService.createBranch(branchDto);
        return ResponseEntity.ok(newBranch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable Long id ) throws Exception {
       BranchDto  existingBranch = _branchService.getBranchById(id);
       return ResponseEntity.ok(existingBranch);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDto>> getAllBranchesByStoreId(@PathVariable Long storeId ) {
        List<BranchDto>  existingBranches = _branchService.getAllBranchesByStoreId( storeId);
        return ResponseEntity.ok(existingBranches);
    }

    @PutMapping("/{id}") //should be put or patch??
    public ResponseEntity<BranchDto> updateBranch(@RequestBody BranchDto branchDto, @PathVariable Long id) throws Exception {
        BranchDto updatedBranch = _branchService.updateBranch(id, branchDto);
        return ResponseEntity.ok(updatedBranch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id ) throws Exception {
        _branchService.deleteBranch(id);
        ApiResponse response = new ApiResponse();
        response.setMessage("branch deleted successfully");
        return ResponseEntity.ok(response);
    }

}

