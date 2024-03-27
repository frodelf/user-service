package com.example.userservice.service.impl;

import com.example.userservice.dto.user.UserDtoForAdd;
import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.entity.users.Consumer;
import com.example.userservice.entity.users.Notary;
import com.example.userservice.mapper.user.UserMapperForAdd;
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
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class NotaryServiceImpl implements NotaryService {
    private final UserMapperForViewAll userMapperForViewAll;
    private final UserMapperForAdd userMapperForAdd;
    private final NotaryRepository notaryRepository;
    private final UserService userService;

    @Override
    public Page<UserDtoForViewAll> getAll(Integer page, Integer pageSize) {
        log.info("ConsumerServiceImpl-getAll start");
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("id")));
        Page<UserDtoForViewAll> result = userMapperForViewAll.toDtoPage(notaryRepository.findAll(pageable));
        log.info("ConsumerServiceImpl-getAll finish");
        return result;
    }

    @Override
    @Transactional
    public void add(UserDtoForAdd userDtoForAdd) throws IOException {
        log.info("ConsumerServiceImpl-add start");
        userService.save(userMapperForAdd.updateNotary(userDtoForAdd, userService));
        log.info("ConsumerServiceImpl-add finish");
    }

    @Override
    @Transactional
    public void addUserForAuthNotary(Long userId) {
        log.info("ConsumerServiceImpl-addUserForAuthNotary start");
        Notary notary = (Notary) userService.getAuthUser();
        notary.getConsumer().add((Consumer) userService.getById(userId));
        userService.save(notary);
        log.info("ConsumerServiceImpl-addUserForAuthNotary finish");
    }
}