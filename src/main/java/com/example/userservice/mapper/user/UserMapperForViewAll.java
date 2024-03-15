package com.example.userservice.mapper.user;

import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.entity.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
// TODO: 13.03.2024 доробить
//@Mapper(componentModel = "spring")
//public interface UserMapperForViewAll {
//    @Mapping(target = "image", ignore = true)
//    UserDtoForViewAll toDto(User user);
//    default UserDtoForViewAll toDto(User user, MinioService minioService) throws ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, IOException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
//        UserDtoForViewAll userDtoForViewAll = toDto(user);
//        userDtoForViewAll.setImage(minioService.getUrl(user.getImage()));
//        return userDtoForViewAll;
//    }
//    default Page<UserDtoForViewAll> toDtoPage(Page<? extends User> users, MinioService minioService){
//        return new PageImpl<>(users.getContent().stream()
//                .map(notary -> {
//                    try {
//                        return toDto(notary, minioService);
//                    } catch (ServerException | XmlParserException | InternalException | InvalidResponseException |
//                             InvalidKeyException | InsufficientDataException | ErrorResponseException |
//                             NoSuchAlgorithmException | IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                })
//                .collect(Collectors.toList()), users.getPageable(), users.getTotalElements());
//    }
//}