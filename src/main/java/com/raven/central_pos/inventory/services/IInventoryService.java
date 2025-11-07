package com.raven.central_pos.inventory.services;

import com.raven.central_pos.inventory.contracts.InventoryDto;

import java.util.List;

public interface IInventoryService {
    InventoryDto createInventory(InventoryDto inventoryDto) throws Exception;
    InventoryDto updateInventory(Long id, InventoryDto inventoryDto) throws Exception;
    void deleteInventory(Long id) throws Exception;
    InventoryDto getInventoryById(Long id) throws Exception;
    InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId);
    List<InventoryDto> getAllInventoriesByBranchId(Long branchId);
}
