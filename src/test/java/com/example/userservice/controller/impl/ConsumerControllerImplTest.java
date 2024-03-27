package com.example.userservice.controller.impl;

import com.example.userservice.dto.consumer.ConsumerDtoForAdd;
import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.entity.enums.StatusUser;
import com.example.userservice.service.ConsumerService;
import com.example.userservice.validator.ConsumerValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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
class ConsumerControllerImplTest {
    @Mock
    private ConsumerService consumerService;
    @Mock
    private ConsumerValidator consumerValidator;
    @InjectMocks
    private ConsumerControllerImpl consumerController;
    @Test
    void getAll() {
        Integer page = 0;
        Integer pageSize = 10;
        String consumerName = "TestConsumerName";
        Page<UserDtoForViewAll> expectedPage = mock(Page.class);
        when(consumerService.getAll(page, pageSize, consumerName, StatusUser.ACTIVE)).thenReturn(expectedPage);

        ResponseEntity<Page<UserDtoForViewAll>> response = consumerController.getAll(page, pageSize, consumerName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPage, response.getBody());
        verify(consumerService).getAll(page, pageSize, consumerName, StatusUser.ACTIVE);
    }
    @Test
    void getAllBlocked() {
        Integer page = 0;
        Integer pageSize = 10;
        String consumerName = "TestConsumerName";
        Page<UserDtoForViewAll> expectedPage = mock(Page.class);
        when(consumerService.getAll(page, pageSize, consumerName, StatusUser.REMOVE)).thenReturn(expectedPage);

        ResponseEntity<Page<UserDtoForViewAll>> response = consumerController.getAllBlocked(page, pageSize, consumerName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPage, response.getBody());
        verify(consumerService).getAll(page, pageSize, consumerName, StatusUser.REMOVE);
    }
    @Test
    void changeStatusForConsumer() {
        Long consumerId = 1L;
        StatusUser statusUser = StatusUser.ACTIVE;

        ResponseEntity<String> response = consumerController.changeStatusForConsumer(consumerId, statusUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("changed", response.getBody());
        verify(consumerService).changeStatusById(consumerId, statusUser);
    }
    @Test
    void add() throws IOException {
        ConsumerDtoForAdd consumerDtoForAdd = new ConsumerDtoForAdd();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<Map<String, String>> response = consumerController.add(consumerDtoForAdd, bindingResult);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonMap("status", "saved"), response.getBody());
        verify(consumerService).add(consumerDtoForAdd);
    }
    @Test
    void addWithException() throws IOException {
        ConsumerDtoForAdd consumerDtoForAdd = new ConsumerDtoForAdd();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(new FieldError("field", "field", "message")));

        ResponseEntity<Map<String, String>> response = consumerController.add(consumerDtoForAdd, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().containsKey("field"));
        assertTrue(response.getBody().containsValue("message"));
        verifyNoInteractions(consumerService);
    }
    @Test
    void addLikeBuilding() {
        Long buildingId = 1L;

        ResponseEntity<String> response = consumerController.addLikeBuilding(buildingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("changed", response.getBody());
        verify(consumerService).addToLikeBuildings(buildingId);
    }
    @Test
    void addLikeFlat() {
        Long flatId = 1L;

        ResponseEntity<String> response = consumerController.addLikeFlat(flatId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("changed", response.getBody());
        verify(consumerService).addToLikeFlats(flatId);
    }
    @Test
    void deleteLikeBuilding() {
        Long buildingId = 1L;

        ResponseEntity<String> response = consumerController.deleteLikeBuilding(buildingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("deleted", response.getBody());
        verify(consumerService).deleteFromLikeBuildings(buildingId);
    }
    @Test
    void deleteLikeFlat() {
        Long flatId = 1L;

        ResponseEntity<String> response = consumerController.deleteLikeFlat(flatId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("deleted", response.getBody());
        verify(consumerService).deleteFromLikeFlats(flatId);
    }
}