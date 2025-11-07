package com.raven.central_pos.user.services;

import com.raven.central_pos.user.contracts.dto.UserDto;
import com.raven.central_pos.user.domain.UserRole;
import com.raven.central_pos.user.model.User;

import java.util.List;

public interface IEmployeeService {
    UserDto createEmployee(UserDto employee, Long storeId) throws Exception;
    UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception;
    User updateEmployee (Long employeeId, UserDto employeeDetails) throws Exception;
    void deleteEmployee(Long employeeId) throws Exception;
    List<UserDto> findEmployees(Long storeId, UserRole role) throws Exception;
    List<UserDto> findBranchEmployees(Long branchId, UserRole role) throws Exception;
}
