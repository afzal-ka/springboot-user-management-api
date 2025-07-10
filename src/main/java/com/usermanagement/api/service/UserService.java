package com.usermanagement.api.service;

import com.usermanagement.api.dto.UserRequestDto;
import com.usermanagement.api.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto requestDto);
    UserResponseDto createUser(UserRequestDto requestDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getById(Long id);
    UserResponseDto updateUser(Long id, UserRequestDto requestDto);
    void deleteUser(Long id);
}
