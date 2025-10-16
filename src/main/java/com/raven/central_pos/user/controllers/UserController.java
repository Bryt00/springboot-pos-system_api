package com.raven.central_pos.user.controllers;

import com.raven.central_pos.core.exceptions.UserException;
import com.raven.central_pos.user.mapper.UserMapper;
import com.raven.central_pos.user.contracts.dto.UserDto;
import com.raven.central_pos.user.model.User;
import com.raven.central_pos.user.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(
            @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User user = userServiceImpl.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(UserMapper.toDTO(user));

    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
           // @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws UserException, Exception {
        User user = userServiceImpl.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDTO(user));

    }

}
