package com.example.userservice.mapper.user;

import com.example.userservice.dto.user.UserDtoForAdd;
import com.example.userservice.entity.users.Builder;
import com.example.userservice.entity.users.Notary;
import com.example.userservice.entity.users.User;
import com.example.userservice.service.UserService;
import com.example.userservice.service.client.MinioServiceClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.io.IOException;


@Mapper(componentModel = "spring")
public interface UserMapperForAdd {
    @Mapping(target = "image", ignore = true)
    void updateEntity(UserDtoForAdd userDtoForAdd, @MappingTarget User user);
    default Notary updateNotary(UserDtoForAdd userDtoForAdd, UserService userService) throws IOException {
        Notary notary = new Notary();
        if(userDtoForAdd.getId()!=null) notary = (Notary) userService.getById(userDtoForAdd.getId());
        updateEntity(userDtoForAdd, notary);
        if(notary.getImage()!=null) MinioServiceClient.deleteImage(notary.getImage());
        notary.setImage(MinioServiceClient.save(userDtoForAdd.getImage()));
        return notary;
    }

    default User updateBuilder(UserDtoForAdd userDtoForAdd, UserService userService) throws IOException {
        Builder builder = new Builder();
        if(userDtoForAdd.getId()!=null) builder = (Builder) userService.getById(userDtoForAdd.getId());
        updateEntity(userDtoForAdd, builder);
        if(builder.getImage()!=null) MinioServiceClient.deleteImage(builder.getImage());
        builder.setImage(MinioServiceClient.save(userDtoForAdd.getImage()));
        return builder;
    };
}