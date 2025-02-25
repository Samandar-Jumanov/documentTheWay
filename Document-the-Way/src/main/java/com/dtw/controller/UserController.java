package com.dtw.controller;

import com.dtw.dtos.UserDto;
import com.dtw.serviceImpl.UserServiceImpl;
import com.dtw.utils.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// base url
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Tag(name = "User")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/auth/register")
    @Operation(summary = "Register a new user", security = {})  // Empty security array means no auth required
    public ResponseEntity<UserDto> createUser(
            @Valid @RequestBody UserDto userDto
    ) {
        UserDto newUser = this.userServiceImpl.createUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    @Operation(summary = "Login user and generate token", security = {})  // Empty security array means no auth required
    public ResponseEntity<String> loginAcc(@Valid @RequestBody UserDto userDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
            );

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(userDto.getUsername());
                return new ResponseEntity<>(token, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
            }
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return new ResponseEntity<>("Authentication failed: " + e.getMessage(),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<UserDto> getSingleUser(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(this.userServiceImpl.getSingleUser(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all users", security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(this.userServiceImpl.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<UserDto> updateUser(
            @RequestBody UserDto userDto,
            @PathVariable Long id
    ) {
        UserDto updatedUser = this.userServiceImpl.updateUser(userDto, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/same-reposts/{repostId}")
    @Operation(summary = "Find users with the same repost", security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<List<UserDto>> findUsersSameReposts(
            @PathVariable Long repostId
    ) {
        List<UserDto> users = userServiceImpl.findUsersByRepost(repostId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}