package com.dtw.controller;


import com.dtw.dtos.UserDto;
import com.dtw.serviceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// base url
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserServiceImpl userServiceImpl;

    @PostMapping
    public ResponseEntity<UserDto> createUser (
            @Valid @RequestBody UserDto userDto
    ){
        UserDto newUser = this.userServiceImpl.createUser(userDto);
        return  new ResponseEntity<>(newUser , HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getSingleUser(
            @PathVariable Long id
    ){
        return  new ResponseEntity<>(this.userServiceImpl.getSingleUser(id) , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>>  getAllUsers(){
        return new ResponseEntity<>(this.userServiceImpl.getAllUsers() , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser (
            @RequestBody UserDto userDto,
            @PathVariable Long id
    ){
        UserDto updatedUser = this.userServiceImpl.updateUser(userDto , id);
        return  new ResponseEntity<>(updatedUser , HttpStatus.OK);
    }
}
