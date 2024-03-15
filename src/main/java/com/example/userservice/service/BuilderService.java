package com.example.userservice.service;

import com.example.userservice.dto.user.UserDtoForAdd;

import java.io.IOException;

public interface BuilderService {
    void add(UserDtoForAdd userDtoForAdd) throws IOException;
}