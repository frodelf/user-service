package com.example.userservice.service.impl;

import com.example.userservice.dto.user.UserDtoForAdd;
import com.example.userservice.entity.users.Builder;
import com.example.userservice.entity.users.User;
import com.example.userservice.mapper.user.UserMapperForAdd;
import com.example.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.mockito.Mockito.*;

@SpringBootTest
class BuilderServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapperForAdd userMapperForAdd;

    @InjectMocks
    private BuilderServiceImpl builderService;

    @Test
    void add() throws IOException {
        UserDtoForAdd userDtoForAdd = new UserDtoForAdd();
        Builder builder = mock(Builder.class);
        when(userMapperForAdd.updateBuilder(userDtoForAdd, userService)).thenReturn(builder);
        doNothing().when(userService).save(builder);

        builderService.add(userDtoForAdd);

        verify(userMapperForAdd).updateBuilder(userDtoForAdd, userService);
        verify(userService).save(builder);
    }
}