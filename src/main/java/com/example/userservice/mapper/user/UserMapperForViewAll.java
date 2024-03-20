package com.example.userservice.mapper.user;

import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.entity.users.User;
import com.example.userservice.service.client.MinioServiceClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapperForViewAll {
    @Mapping(target = "image", ignore = true)
    UserDtoForViewAll toDto(User user);

    default UserDtoForViewAll toDtoEntity(User user) {
        UserDtoForViewAll userDtoForViewAll = toDto(user);
        userDtoForViewAll.setImage(MinioServiceClient.getUrl(user.getImage()));
        return userDtoForViewAll;
    }

    default Page<UserDtoForViewAll> toDtoPage(Page<? extends User> users) {
        return new PageImpl<>(users.getContent().stream()
                .map(this::toDtoEntity)
                .collect(Collectors.toList()), users.getPageable(), users.getTotalElements());
    }
}