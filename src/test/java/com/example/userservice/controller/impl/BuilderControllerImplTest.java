package com.example.userservice.controller.impl;

import com.example.userservice.dto.user.UserDtoForAdd;
import com.example.userservice.service.BuilderService;
import com.example.userservice.validator.UserValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BuilderControllerImplTest {
    @Mock
    private UserValidator userValidator;
    @Mock
    private BuilderService builderService;
    @InjectMocks
    private BuilderControllerImpl builderController;
    @Test
    void add() throws IOException {
        UserDtoForAdd userDtoForAdd = new UserDtoForAdd();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<Map<String, String>> response = builderController.add(userDtoForAdd, bindingResult);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonMap("status", "saved"), response.getBody());
        verify(builderService).add(userDtoForAdd);
    }
    @Test
    void addWhenReturnBadRequestStatus() throws IOException {
        UserDtoForAdd userDtoForAdd = new UserDtoForAdd();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(new FieldError("field", "field", "message")));

        ResponseEntity<Map<String, String>> response = builderController.add(userDtoForAdd, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().containsKey("field"));
        assertTrue(response.getBody().containsValue("message"));
        verifyNoInteractions(builderService);
    }
}