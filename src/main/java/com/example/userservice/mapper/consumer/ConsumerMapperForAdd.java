package com.example.userservice.mapper.consumer;

import com.example.userservice.dto.consumer.ConsumerDtoForAdd;
import com.example.userservice.entity.users.Consumer;
import com.example.userservice.service.UserService;
import com.example.userservice.service.impl.MinioServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface ConsumerMapperForAdd {
    @Mapping(target = "image", ignore = true)
    void updateEntity(ConsumerDtoForAdd consumerDtoForAdd, @MappingTarget Consumer consumer);
    default Consumer updateEntity(ConsumerDtoForAdd consumerDtoForAdd, UserService userService) throws IOException {
        Consumer consumer = new Consumer();
        if(consumerDtoForAdd.getId()!=null) consumer = (Consumer) userService.getById(consumerDtoForAdd.getId());
        updateEntity(consumerDtoForAdd, consumer);
        if(consumer.getImage()!=null) MinioServiceImpl.deleteImage(consumer.getImage());
        consumer.setImage(MinioServiceImpl.save(consumerDtoForAdd.getImage()));
        return consumer;
    }
}