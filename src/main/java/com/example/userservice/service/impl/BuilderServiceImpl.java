package com.example.userservice.service.impl;

import com.example.userservice.dto.user.UserDtoForAdd;
import com.example.userservice.mapper.user.UserMapperForAdd;
import com.example.userservice.service.BuilderService;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class BuilderServiceImpl implements BuilderService {
    private final UserService userService;
    private final UserMapperForAdd userMapperForAdd;
    @Override
    public void add(UserDtoForAdd userDtoForAdd) throws IOException {
        log.info("BuilderServiceImpl-add start");
        userService.save(userMapperForAdd.updateBuilder(userDtoForAdd, userService));
        log.info("BuilderServiceImpl-add finish");
    }
}