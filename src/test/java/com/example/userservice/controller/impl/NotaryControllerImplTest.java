package com.example.userservice.controller.impl;

import com.example.userservice.dto.user.UserDtoForAdd;
import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.service.NotaryService;
import com.example.userservice.service.UserService;
import com.example.userservice.validator.UserValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class NotaryControllerImplTest {
    @Mock
    private NotaryService notaryService;
    @Mock
    private UserService userService;
    @Mock
    private UserValidator userValidator;
    @InjectMocks
    private NotaryControllerImpl notaryController;
    @Test
    void getAll() {
        Integer page = 0;
        Integer pageSize = 10;
        Page<UserDtoForViewAll> expectedPage = mock(Page.class);
        when(notaryService.getAll(page, pageSize)).thenReturn(expectedPage);

        ResponseEntity<Page<UserDtoForViewAll>> response = notaryController.getAll(page, pageSize);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPage, response.getBody());
        verify(notaryService).getAll(page, pageSize);
    }
    @Test
    void add() throws IOException {
        UserDtoForAdd userDtoForAdd = mock(UserDtoForAdd.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<Map<String, String>> response = notaryController.add(userDtoForAdd, bindingResult);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("saved", response.getBody().get("status"));
        verify(userValidator).validate(userDtoForAdd, bindingResult);
        verify(notaryService).add(userDtoForAdd);
    }
    @Test
    void addWithException() throws IOException {
        UserDtoForAdd userDtoForAdd = mock(UserDtoForAdd.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<Map<String, String>> response = notaryController.add(userDtoForAdd, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(userValidator).validate(userDtoForAdd, bindingResult);
        verify(notaryService, never()).add(userDtoForAdd);
    }
    @Test
    void deleteById() {
        Long notaryId = 1L;

        ResponseEntity<String> response = notaryController.deleteById(notaryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("deleted", response.getBody());
        verify(userService).deleteById(notaryId);
    }
    @Test
    void addUserForAuthNotary() {
        Long userId = 1L;

        ResponseEntity<String> response = notaryController.addUserForAuthNotary(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("updated", response.getBody());
        verify(notaryService).addUserForAuthNotary(userId);
    }
}