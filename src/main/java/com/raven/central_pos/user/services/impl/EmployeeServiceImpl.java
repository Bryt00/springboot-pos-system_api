package com.raven.central_pos.user.services.impl;

import com.raven.central_pos.branch.model.Branch;
import com.raven.central_pos.branch.repository.BranchRepository;
import com.raven.central_pos.store.model.Store;
import com.raven.central_pos.store.repository.StoreRepository;
import com.raven.central_pos.user.contracts.dto.UserDto;
import com.raven.central_pos.user.domain.UserRole;
import com.raven.central_pos.user.mapper.UserMapper;
import com.raven.central_pos.user.model.User;
import com.raven.central_pos.user.repository.UserRepository;
import com.raven.central_pos.user.services.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private final UserRepository _userRepository;

    private final StoreRepository _storeRepository;
    private final PasswordEncoder passwordEncoder;
    private final BranchRepository _branchRepository;
    @Override
    public UserDto createEmployee(UserDto employee, Long storeId) throws Exception {
        Store existingStore = _storeRepository.findById(storeId).orElseThrow(
                () -> new Exception("store not found...")
        );
        //Branch branch = null;

        Branch branch = null;
        if (employee.getRole() == UserRole.ROLE_BRANCH_MANAGER) {
            if (employee.getBranchId() == null) {
                throw new Exception("branch id is required to create branch manager");
            }
            branch = _branchRepository.findById(employee.getBranchId()).orElseThrow(
                    () -> new Exception("branch not found...")
            );
        }
        User user = UserMapper.toEntity(employee);
        user.setStore(existingStore);
        user.setBranch(branch);
        user.setPassword(passwordEncoder.encode(employee.getPassword()));

        User createdEmployee = _userRepository.save(user);
        if (employee.getRole() == UserRole.ROLE_BRANCH_MANAGER && branch != null) {
            branch.setManager(createdEmployee);
            _branchRepository.save(branch);
        }
        return UserMapper.toDTO(createdEmployee);
    }

    @Override
    public UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception {
       Branch branch = _branchRepository.findById(branchId).orElseThrow(
                ()->  new Exception("branch not found...")
        );
       if(employee.getRole()==UserRole.ROLE_BRANCH_CASHIER ||employee.getRole()==UserRole.ROLE_BRANCH_MANAGER){
           User user = UserMapper.toEntity(employee);
           user.setBranch(branch);
           user.setPassword(passwordEncoder.encode(employee.getPassword()));
           return UserMapper.toDTO(_userRepository.save(user));
       }
        throw new Exception("branch role not supported");
    }

    @Override
    public User updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception {
        User existingEmployee = _userRepository.findById(employeeId).orElseThrow(
                ()->new Exception("employee with "+employeeId+" does not exist")
        );
        Branch branch = _branchRepository.findById(employeeDetails.getBranchId()).orElseThrow(
                ()->new Exception("branch not found...")
        );
        existingEmployee.setFullName(employeeDetails.getFullName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setPhone(employeeDetails.getPhone());
        existingEmployee.setRole(employeeDetails.getRole());
        existingEmployee.setBranch(branch);
        existingEmployee.setUpdatedAt(LocalDateTime.now());

        return _userRepository.save(existingEmployee);


       // return null;
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
        User employee = _userRepository.findById(employeeId).orElseThrow(
                ()->new Exception("employee not found...")
        );
        _userRepository.delete(employee);
    }

    @Override
    public List<UserDto> findEmployees(Long storeId, UserRole role) throws Exception {
        Store existingStore = _storeRepository.findById(storeId).orElseThrow(
                ()-> new Exception("store not found...")
        );
        return _userRepository.findByStore(existingStore )
                .stream()
                .filter(user -> role==null||user.getRole()==role)
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findBranchEmployees(Long branchId, UserRole role) throws Exception {
        Branch branch = _branchRepository.findById(branchId).orElseThrow(
                ()->  new Exception("branch not found...")
        );

        List<UserDto> employees = _userRepository.findByBranchId(branchId)
                .stream().filter(user -> role==null||user.getRole()==role)
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
        return employees;
    }
}
