package com.example.userservice.mapper.notary;

import com.example.userservice.dto.notary.NotaryDtoForAdd;
import com.example.userservice.entity.users.Notary;
import com.example.userservice.service.UserService;
import com.example.userservice.service.impl.MinioServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.io.IOException;


@Mapper(componentModel = "spring")
public interface NotaryMapperForAdd {
    @Mapping(target = "image", ignore = true)
    void updateEntity(NotaryDtoForAdd notaryDtoForAdd, @MappingTarget Notary notary);
    default Notary updateEntity(NotaryDtoForAdd notaryDtoForAdd, UserService userService) throws IOException {
        Notary notary = new Notary();
        if(notaryDtoForAdd.getId()!=null) notary = (Notary) userService.getById(notaryDtoForAdd.getId());
        updateEntity(notaryDtoForAdd, notary);
        if(notary.getImage()!=null) MinioServiceImpl.deleteImage(notary.getImage());
        notary.setImage(MinioServiceImpl.save(notaryDtoForAdd.getImage()));
        return notary;
    }
}