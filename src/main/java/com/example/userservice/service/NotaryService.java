package com.example.userservice.service;

import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.dto.notary.NotaryDtoForAdd;

import org.springframework.data.domain.Page;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface NotaryService {
    Page<UserDtoForViewAll> getAll(Integer page, Integer pageSize);
    void add(NotaryDtoForAdd notaryDtoForAdd) throws IOException;
}