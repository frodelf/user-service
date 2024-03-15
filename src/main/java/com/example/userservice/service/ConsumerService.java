package com.example.userservice.service;

import com.example.userservice.dto.consumer.ConsumerDtoForAdd;
import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.entity.enums.StatusUser;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface ConsumerService {
    Page<UserDtoForViewAll> getAll(Integer page, Integer pageSize, String consumerName, StatusUser statusUser);
    void changeStatusById(Long consumerId, StatusUser statusUser);
    void add(ConsumerDtoForAdd consumerDtoForAdd) throws IOException;
}