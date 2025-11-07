package com.raven.central_pos.inventory.services.impl;

import com.raven.central_pos.branch.model.Branch;
import com.raven.central_pos.branch.repository.BranchRepository;
import com.raven.central_pos.inventory.contracts.InventoryDto;
import com.raven.central_pos.inventory.mapper.InventoryMapper;
import com.raven.central_pos.inventory.model.Inventory;
import com.raven.central_pos.inventory.repository.InventoryRepository;
import com.raven.central_pos.inventory.services.IInventoryService;
import com.raven.central_pos.product.models.Product;
import com.raven.central_pos.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements IInventoryService {
    private final InventoryRepository _inventoryRepository;
    private final BranchRepository _branchRepository;
    private final ProductRepository _productRepository;

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) throws Exception {
        Branch existingBranch = _branchRepository.findById(inventoryDto.getBranchId()).orElseThrow(
                () -> new Exception("branch not found")
        );
        Product product = _productRepository.findById(inventoryDto.getProductId()).orElseThrow(
                () -> new Exception("product not found")
        );
        Inventory newInventory = InventoryMapper.toEntity(inventoryDto, existingBranch, product);
        Inventory savedInventory = _inventoryRepository.save(newInventory);

        return InventoryMapper.toDto(savedInventory);
    }

    @Override
    public InventoryDto updateInventory(Long id, InventoryDto inventoryDto) throws Exception {
        Inventory inventory = _inventoryRepository.findById(id).orElseThrow(
                () -> new Exception("inventory not found")
        );
        inventory.setQuantity(inventoryDto.getQuantity());
        inventory.setLastUpdate(LocalDateTime.now());
        Inventory updatedInventory = _inventoryRepository.save(inventory);

        return InventoryMapper.toDto(updatedInventory);
    }

    @Override
    public void deleteInventory(Long id) throws Exception {
        Inventory inventory = _inventoryRepository.findById(id).orElseThrow(
                () -> new Exception("inventory not found")
        );
        _inventoryRepository.delete(inventory);
    }

    @Override
    public InventoryDto getInventoryById(Long id) throws Exception {
        Inventory inventory = _inventoryRepository.findById(id).orElseThrow(
                () -> new Exception("inventory not found")
        );
        return InventoryMapper.toDto(inventory);
    }

    @Override
    public InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        Inventory inventory = _inventoryRepository.findByProductIdAndBranchId(productId, branchId);
        return InventoryMapper.toDto(inventory);

    }

    @Override
    public List<InventoryDto> getAllInventoriesByBranchId(Long branchId) {
        List<Inventory> inventories = _inventoryRepository.findByBranchId(branchId);
        return inventories.stream().map(
                InventoryMapper::toDto
        ).collect(Collectors.toList());
    }
}
