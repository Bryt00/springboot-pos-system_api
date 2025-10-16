package com.raven.central_pos.user.services;

import com.raven.central_pos.core.exceptions.UserException;
import com.raven.central_pos.user.model.User;

import java.util.List;

public interface IUserService {
    User getUserFromJwtToken(String token) throws UserException;
    User getCurrentUser() throws UserException;
    User getUserByEmail(String email) throws UserException;
    User getUserById(Long id) throws UserException, Exception;
    List<User> getAllUsers();
}
