package com.raven.central_pos.user.services;

import com.raven.central_pos.core.exceptions.UserException;
import com.raven.central_pos.user.contracts.dto.UserDto;
import com.raven.central_pos.user.contracts.response.AuthResponse;

public interface IAuthService {
    AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto) throws UserException;
}
