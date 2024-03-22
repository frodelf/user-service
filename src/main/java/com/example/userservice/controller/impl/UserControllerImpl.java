package com.example.userservice.controller.impl;

import com.example.userservice.controller.UserController;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;
    @GetMapping("/auth")
    public ResponseEntity<Long> getIdAuthUser(){
        return ResponseEntity.ok(userService.getAuthUser().getId());
    }
}