package com.raven.central_pos.inventory.controller;

import com.raven.central_pos.core.api_response.ApiResponse;
import com.raven.central_pos.inventory.contracts.InventoryDto;
import com.raven.central_pos.inventory.services.IInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {
    private final IInventoryService _inventoryService;

    @PostMapping()
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto) throws Exception {
        return ResponseEntity.ok(_inventoryService.createInventory(inventoryDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> update(
            @RequestBody InventoryDto inventoryDto,
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(_inventoryService.updateInventory(id, inventoryDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(
           // @RequestBody InventoryDto inventoryDto,
            @PathVariable Long id) throws Exception {
       _inventoryService.deleteInventory(id);
       ApiResponse apiResponse = new ApiResponse();
       apiResponse.setMessage("inventory deleted successfully");
       return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/branch/{branchId}/product/{productId}")
    public ResponseEntity<InventoryDto> getInventoryByProductIdAndBranchId(
            @PathVariable Long branchId, @PathVariable Long productId) throws Exception {
        return ResponseEntity.ok(_inventoryService.getInventoryByProductIdAndBranchId(productId,branchId));
    }
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<InventoryDto>> getInventoryByBranch(
            @PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(_inventoryService.getAllInventoriesByBranchId(branchId));
    }
}
