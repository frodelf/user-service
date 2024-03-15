package com.example.userservice.mapper.notary;

import com.example.userservice.dto.notary.NotaryDtoForAdd;
import com.example.userservice.entity.users.Notary;
import com.example.userservice.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
// TODO: 13.03.2024 доробить
//@Mapper(componentModel = "spring")
//public interface NotaryMapperForAdd {
//    @Mapping(target = "image", ignore = true)
//    void updateEntity(NotaryDtoForAdd notaryDtoForAdd, @MappingTarget Notary notary);
//    default Notary updateEntity(NotaryDtoForAdd notaryDtoForAdd, MinioService minioService, UserService userService) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
//        Notary notary = new Notary();
//        if(notaryDtoForAdd.getId()!=null) notary = (Notary) userService.getById(notaryDtoForAdd.getId());
//        if(notary.getImage()!=null) minioService.deleteImg(notary.getImage());
//        notary.setImage(minioService.putMultipartFile(notaryDtoForAdd.getImage()));
//        return notary;
//    }
//}