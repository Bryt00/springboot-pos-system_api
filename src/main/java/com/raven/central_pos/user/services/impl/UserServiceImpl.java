package com.raven.central_pos.user.services.impl;

import com.raven.central_pos.core.configuration.JwtProvider;
import com.raven.central_pos.core.exceptions.UserException;
import com.raven.central_pos.user.model.User;
import com.raven.central_pos.user.repository.UserRepository;
import com.raven.central_pos.user.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public User getUserFromJwtToken(String token) throws UserException {
        String email = jwtProvider.getEmailFromToken(token);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserException("Invalid token");
        }
        return user;
    }

    @Override
    public User getCurrentUser() throws UserException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByEmail(email);
        if (currentUser == null) {
            throw new UserException("user not found");
        }
        return currentUser;
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserException("user with email: " + email + " does not exist");
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws UserException, Exception {

        return userRepository.findById(id).orElseThrow(() -> new Exception("user with id " + id + " not found")

        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
