package com.example.testrit.service;

import java.util.List;

import com.example.testrit.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long userId);
}
