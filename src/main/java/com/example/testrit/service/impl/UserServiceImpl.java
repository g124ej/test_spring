package com.example.testrit.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.testrit.dto.UserDto;
import com.example.testrit.entity.User;
import com.example.testrit.exception.ResourceNotFoundException;
import com.example.testrit.mapper.UserMapper;
import com.example.testrit.repository.UserRepository;
import com.example.testrit.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        // User user = UserMapper.mapToUser(userDto);

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found ! User with id : " + userId));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found ! User with id : " + userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());

        User updatedUserObj = userRepository.save(user);
        return UserMapper.mapToUserDto(updatedUserObj);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found ! User with id : " + userId));
        userRepository.deleteById(userId);
    }

}
