package com.example.userservice.mapper.user;

import com.example.userservice.dto.user.UserDtoForAdd;
import com.example.userservice.entity.users.Notary;
import com.example.userservice.service.UserService;
import com.example.userservice.service.impl.MinioServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.io.IOException;


@Mapper(componentModel = "spring")
public interface UserMapperForAdd {
    @Mapping(target = "image", ignore = true)
    void updateEntity(UserDtoForAdd userDtoForAdd, @MappingTarget Notary notary);
    default Notary updateEntity(UserDtoForAdd userDtoForAdd, UserService userService) throws IOException {
        Notary notary = new Notary();
        if(userDtoForAdd.getId()!=null) notary = (Notary) userService.getById(userDtoForAdd.getId());
        updateEntity(userDtoForAdd, notary);
        if(notary.getImage()!=null) MinioServiceImpl.deleteImage(notary.getImage());
        notary.setImage(MinioServiceImpl.save(userDtoForAdd.getImage()));
        return notary;
    }
}