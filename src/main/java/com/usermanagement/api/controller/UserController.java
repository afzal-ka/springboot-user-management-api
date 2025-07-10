package com.usermanagement.api.controller;

import com.usermanagement.api.dto.UserRequestDto;
import com.usermanagement.api.dto.UserResponseDto;
import com.usermanagement.api.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "User Controller", description = "APIs for registering, creating, retrieving, updating, and deleting users")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl service;

    @Operation(summary = "Register a new user (public endpoint)")
    @ApiResponse(responseCode = "200", description = "User registered successfully")
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto requestDto){
        return new ResponseEntity<>(service.registerUser(requestDto), HttpStatus.OK);
    }

    @Operation(summary = "Create a new user (admin)")
    @ApiResponse(responseCode = "200", description = "User created successfully")
    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto requestDto){
        return new ResponseEntity<>(service.createUser(requestDto), HttpStatus.OK);
    }

    @Operation(summary = "Get list of all users")
    @ApiResponse(responseCode = "200",description = "Users retrieved successfully")
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return new ResponseEntity<>(service.getAllUsers(),HttpStatus.OK);
    }

    @Operation(summary = "Get a user by ID")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id){
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    @Operation(summary = "Update a user by ID")
    @ApiResponse(responseCode = "200",description = "User found")
    @ApiResponse(responseCode = "404",description = "User not found")
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDto requestDto){
        return new ResponseEntity<>(service.updateUser(id,requestDto),HttpStatus.OK);
    }

    @Operation(summary = "Delete a user by ID")
    @ApiResponse(responseCode = "200",description = "User found")
    @ApiResponse(responseCode = "404",description = "User not found")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
