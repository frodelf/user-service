package com.example.userservice.service.impl;

import com.example.userservice.dto.consumer.ConsumerDtoForAdd;
import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.entity.enums.StatusUser;
import com.example.userservice.entity.users.Consumer;
import com.example.userservice.entity.users.User;
import com.example.userservice.mapper.consumer.ConsumerMapperForAdd;
import com.example.userservice.mapper.user.UserMapperForViewAll;
import com.example.userservice.repository.ConsumerRepository;
import com.example.userservice.service.ConsumerService;
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
public class ConsumerServiceImpl implements ConsumerService {
    private final ConsumerRepository consumerRepository;
    private final UserMapperForViewAll userMapperForViewAll;
    private final ConsumerMapperForAdd consumerMapperForAdd;
    private final UserService userService;
    @Override
    public Page<UserDtoForViewAll> getAll(Integer page, Integer pageSize, String consumerName, StatusUser statusUser) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("id")));
        return userMapperForViewAll.toDtoPage(consumerRepository.findAllByNameLikeAndStatus(consumerName, statusUser, pageable));
    }
    @Transactional
    @Override
    public void changeStatusById(Long consumerId, StatusUser statusUser) {
        Consumer consumer;
        try {
            consumer = (Consumer) userService.getById(consumerId);
        }catch (ClassCastException e){
            throw new ClassCastException("User by id = "+consumerId+" isn't Admin");
        }
        consumer.setStatus(statusUser);
        userService.save(consumer);
    }
    @Override
    @Transactional
    public void add(ConsumerDtoForAdd consumerDtoForAdd) throws IOException {
        userService.save(consumerMapperForAdd.updateEntity(consumerDtoForAdd, userService));
    }
    @Override
    @Transactional
    public void addToLikeBuildings(Long buildingId){
        Consumer consumer = (Consumer) userService.getAuthUser();
        consumer.getLikeBuildings().add(buildingId);
        userService.save(consumer);
    }
    @Override
    @Transactional
    public void addToLikeFlats(Long flatId){
        Consumer consumer = (Consumer) userService.getAuthUser();
        consumer.getLikeFlats().add(flatId);
        userService.save(consumer);
    }
    @Override
    @Transactional
    public void deleteFromLikeBuildings(Long buildingId){
        Consumer consumer = (Consumer) userService.getAuthUser();
        consumer.getLikeBuildings().remove(buildingId);
        userService.save(consumer);
    }
    @Override
    @Transactional
    public void deleteFromLikeFlats(Long flatId){
        Consumer consumer = (Consumer) userService.getAuthUser();
        consumer.getLikeFlats().remove(flatId);
        userService.save(consumer);
    }
}