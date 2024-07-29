package com.example.testrit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.testrit.dto.UserDto;
import com.example.testrit.entity.User;
import com.example.testrit.service.AuthenticationService;
import com.example.testrit.service.MailSenderService;
import com.example.testrit.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private AuthenticationService authenticationService;
    private final MailSenderService mailService;

    // REST API Add User
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        mailService.sendNewMail(userDto.getEmail(), "Credential for login to demo application",
                "Username/Email: " + userDto.getEmail() + "\nPassword: " + userDto.getPassword());
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // REST API Get By Id
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    // REST API Get All
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // REST API Update User
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("id") Long userId,
            @RequestBody UserDto updatedUser) {
        UserDto userDto = userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(userDto);
    }

    // REST API Delete
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {

        User currentUser = (User) authenticationService.getUserLogin();

        return ResponseEntity.ok(currentUser);
    }

}
