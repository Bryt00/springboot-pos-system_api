package com.raven.central_pos.store.controller;

import com.raven.central_pos.core.exceptions.UserException;
import com.raven.central_pos.core.api_response.ApiResponse;
import com.raven.central_pos.store.contracts.dto.StoreDto;
import com.raven.central_pos.store.domain.StoreStatus;
import com.raven.central_pos.store.mappers.StoreMapper;
import com.raven.central_pos.store.services.IStoreService;
import com.raven.central_pos.user.model.User;
import com.raven.central_pos.user.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {
    private final IStoreService storeService;
    private final IUserService userService;

    @PostMapping
    public ResponseEntity<StoreDto> createStoreHandler(@RequestBody StoreDto storeDto, @RequestHeader("Authorization")String jwt) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.createStore(storeDto, user));

    }



    @GetMapping("/admin")
    public ResponseEntity<StoreDto> getStoreByAdmin(@RequestHeader("Authorization")String jwt) throws UserException {
        return ResponseEntity.ok(StoreMapper.toDto(storeService.getStoreByAdmin()));
    }

    @GetMapping("/employee")
    public ResponseEntity<StoreDto> getStoreByEmployee(@RequestHeader("Authorization")String jwt) throws UserException {
        return ResponseEntity.ok(storeService.getStoreByEmployee());
    }

    @GetMapping()
    public ResponseEntity<List<StoreDto>> getAllStores(@RequestHeader("Authorization")String jwt){
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_STORE_ADMIN')")
    public ResponseEntity<StoreDto> updateStore(@PathVariable Long id, @RequestBody StoreDto storeDto) throws Exception {
        return ResponseEntity.ok(storeService.updateStore(id, storeDto));
    }

    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDto> moderateStore(@RequestParam StoreStatus status, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(storeService.moderateStore(id, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStoreById (@PathVariable Long id, @RequestHeader("Authorization")String jwt) throws Exception {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id) throws UserException {
        storeService.deleteStore(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("store deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }


}
