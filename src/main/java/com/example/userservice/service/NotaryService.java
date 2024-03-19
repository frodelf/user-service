package com.example.userservice.service;

import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.dto.user.UserDtoForAdd;

import org.springframework.data.domain.Page;

import java.io.IOException;

public interface NotaryService {
    Page<UserDtoForViewAll> getAll(Integer page, Integer pageSize);
    void add(UserDtoForAdd userDtoForAdd) throws IOException;
    void addUserForAuthNotary(Long userId);
}