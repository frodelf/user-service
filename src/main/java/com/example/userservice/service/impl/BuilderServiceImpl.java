package com.example.userservice.service.impl;

import com.example.userservice.dto.user.UserDtoForAdd;
import com.example.userservice.mapper.user.UserMapperForAdd;
import com.example.userservice.service.BuilderService;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
@RequiredArgsConstructor
public class BuilderServiceImpl implements BuilderService {
    private final UserService userService;
    private final UserMapperForAdd userMapperForAdd;
    @Override
    public void add(UserDtoForAdd userDtoForAdd) throws IOException {
        userService.save(userMapperForAdd.updateEntity(userDtoForAdd, userService));
    }
}