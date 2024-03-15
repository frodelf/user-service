package com.example.userservice.service.impl;

import com.example.userservice.dto.notary.NotaryDtoForAdd;
import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.mapper.notary.NotaryMapperForAdd;
import com.example.userservice.mapper.user.UserMapperForViewAll;
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

import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class NotaryServiceImpl implements NotaryService {
    private final UserMapperForViewAll userMapperForViewAll;
    private final NotaryMapperForAdd notaryMapperForAdd;
    private final NotaryRepository notaryRepository;
    private final UserService userService;

    @Override
    public Page<UserDtoForViewAll> getAll(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("id")));
        Page<UserDtoForViewAll> result = userMapperForViewAll.toDtoPage(notaryRepository.findAll(pageable));
        return result;
    }

    @Override
    public void add(NotaryDtoForAdd notaryDtoForAdd) throws IOException {
        userService.save(notaryMapperForAdd.updateEntity(notaryDtoForAdd, userService));

    }
}