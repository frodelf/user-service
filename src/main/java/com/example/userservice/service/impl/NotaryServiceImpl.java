package com.example.userservice.service.impl;

import com.example.userservice.dto.notary.NotaryDtoForAdd;
import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.entity.enums.StatusUser;
import com.example.userservice.entity.users.Notary;
import com.example.userservice.repository.NotaryRepository;
import com.example.userservice.service.NotaryService;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Log4j2
@Service
@RequiredArgsConstructor
public class NotaryServiceImpl implements NotaryService {
    private final NotaryRepository notaryRepository;
    private final UserService userService;

    @Override
    public Page<UserDtoForViewAll> getAll(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("id")));
//        Page<UserDtoForViewAll> result = userMapperForViewAll.toDtoPage(notaryRepository.findAll(pageable), minioService);
        return null;
    }

    @Override
    public void add(NotaryDtoForAdd notaryDtoForAdd) {
//        userService.save(notaryMapperForAdd.updateEntity(notaryDtoForAdd, minioService, userService));

    }
}