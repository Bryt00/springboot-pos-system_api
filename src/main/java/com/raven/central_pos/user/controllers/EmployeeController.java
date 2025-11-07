package com.raven.central_pos.user.controllers;

import com.raven.central_pos.core.api_response.ApiResponse;
import com.raven.central_pos.user.contracts.dto.UserDto;
import com.raven.central_pos.user.domain.UserRole;
import com.raven.central_pos.user.model.User;
import com.raven.central_pos.user.services.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
private final IEmployeeService _employeeService;
    @PostMapping("/store/{storeId}")
    public ResponseEntity<UserDto> createStoreEmployee(
            @RequestBody UserDto userDto, @PathVariable Long storeId) throws Exception {
            UserDto newEmployee = _employeeService.createEmployee(userDto,storeId);
            return ResponseEntity.ok(newEmployee);
    }
    @PostMapping("/branch/{branchId}")
    public ResponseEntity<UserDto> createBranchEmployee(
            @RequestBody UserDto userDto, @PathVariable Long branchId) throws Exception {
        UserDto newEmployee = _employeeService.createBranchEmployee(userDto,branchId);
        return ResponseEntity.ok(newEmployee);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateEmployee(
            @RequestBody UserDto userDto, @PathVariable Long id) throws Exception {
        User existingEmployee = _employeeService.updateEmployee(id,userDto);
        return ResponseEntity.ok(existingEmployee);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(
            @PathVariable Long id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("employee deleted successfully");
        _employeeService.deleteEmployee(id);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/store/{id}")
    public ResponseEntity<List<UserDto>> getEmployees(
            @PathVariable Long id,
            @RequestParam(required = false)UserRole userRole) throws Exception {

     List<UserDto> employees =  _employeeService.findEmployees(id, userRole);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<UserDto>> getBranchEmployees(
            @PathVariable Long id,
            @RequestParam(required = false)UserRole userRole) throws Exception {

        List<UserDto> employees =  _employeeService.findBranchEmployees(id, userRole);
        return ResponseEntity.ok(employees);
    }
}
