package com.raven.central_pos.user.controllers;

import com.raven.central_pos.core.exceptions.UserException;
import com.raven.central_pos.user.contracts.dto.UserDto;
import com.raven.central_pos.user.contracts.response.AuthResponse;
import com.raven.central_pos.user.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;
//http://localhost:8080/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(
           @RequestBody UserDto userDto
    )throws UserException {
        return ResponseEntity.ok(
                authService.signup(userDto)
        );

    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody UserDto userDto
    )throws UserException {
        return ResponseEntity.ok(
                authService.login(userDto)
        );

    }
}
