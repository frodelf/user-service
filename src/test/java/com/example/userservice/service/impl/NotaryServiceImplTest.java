package com.example.userservice.service.impl;

import com.example.userservice.dto.user.UserDtoForAdd;
import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.entity.users.Consumer;
import com.example.userservice.entity.users.Notary;
import com.example.userservice.mapper.user.UserMapperForAdd;
import com.example.userservice.mapper.user.UserMapperForViewAll;
import com.example.userservice.repository.NotaryRepository;
import com.example.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class NotaryServiceImplTest {
    @Mock
    private UserMapperForViewAll userMapperForViewAll;

    @Mock
    private UserMapperForAdd userMapperForAdd;

    @Mock
    private NotaryRepository notaryRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private NotaryServiceImpl notaryService;
    @Test
    void getAll() {
        Page<Notary> notaries = mock(Page.class);
        Page<UserDtoForViewAll> expectedPage = mock(Page.class);
        when(notaryRepository.findAll(any(Pageable.class))).thenReturn(notaries);
        when(userMapperForViewAll.toDtoPage(any())).thenReturn(expectedPage);

        Page<UserDtoForViewAll> result = notaryService.getAll(0, 10);

        assertEquals(expectedPage, result);
    }

    @Test
    void add() throws IOException {
        UserDtoForAdd userDtoForAdd = new UserDtoForAdd();

        notaryService.add(userDtoForAdd);

        verify(userService).save(any());
    }

    @Test
    void addUserForAuthNotary() {
        Long userId = 1L;
        Notary notary = new Notary();
        notary.setConsumer(new ArrayList<>());
        Consumer consumer = new Consumer();
        when(userService.getAuthUser()).thenReturn(notary);
        when(userService.getById(userId)).thenReturn(consumer);

        notaryService.addUserForAuthNotary(userId);

        assertTrue(notary.getConsumer().contains(consumer));
        verify(userService).save(notary);
    }
}