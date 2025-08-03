package com.maia.vinicius.controller;

import com.maia.vinicius.dto.UserRequest;
import com.maia.vinicius.mapper.UserMapper;
import com.maia.vinicius.model.User;
import com.maia.vinicius.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserRequest userRequest){
        userService.registerUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
