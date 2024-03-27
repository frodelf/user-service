package com.example.userservice.service.impl;

import com.example.userservice.dto.consumer.ConsumerDtoForAdd;
import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.entity.enums.StatusUser;
import com.example.userservice.entity.users.Consumer;
import com.example.userservice.entity.users.User;
import com.example.userservice.mapper.consumer.ConsumerMapperForAdd;
import com.example.userservice.mapper.user.UserMapperForViewAll;
import com.example.userservice.repository.ConsumerRepository;
import com.example.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ConsumerServiceImplTest {
    @Mock
    private ConsumerRepository consumerRepository;
    @Mock
    private UserMapperForViewAll userMapperForViewAll;
    @Mock
    private ConsumerMapperForAdd consumerMapperForAdd;
    @Mock
    private UserService userService;
    @InjectMocks
    private ConsumerServiceImpl consumerService;
    @Test
    void getAll() {
        Integer page = 0;
        Integer pageSize = 10;
        String consumerName = "test";
        StatusUser statusUser = StatusUser.ACTIVE;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("id")));
        Page<UserDtoForViewAll> expectedPage = mock(Page.class);
        Page<Consumer> userDtoList = mock(Page.class);
        when(userMapperForViewAll.toDtoPage(any())).thenReturn(expectedPage);
        when(consumerRepository.findAllByNameLikeAndStatus(consumerName, statusUser, pageable)).thenReturn(userDtoList);

        Page<UserDtoForViewAll> result = consumerService.getAll(page, pageSize, consumerName, statusUser);

        verify(userMapperForViewAll).toDtoPage(any());
        verify(consumerRepository).findAllByNameLikeAndStatus(consumerName, statusUser, pageable);
        assertEquals(expectedPage, result);
    }
    @Test
    void changeStatusById() {
        Long consumerId = 1L;
        StatusUser statusUser = StatusUser.ACTIVE;
        Consumer consumer = new Consumer();
        when(userService.getById(consumerId)).thenReturn(consumer);
        doNothing().when(userService).save(consumer);

        consumerService.changeStatusById(consumerId, statusUser);

        assertEquals(statusUser, consumer.getStatus());
        verify(userService).save(consumer);
    }

    @Test
    void add() throws IOException {
        ConsumerDtoForAdd consumerDtoForAdd = new ConsumerDtoForAdd();
        Consumer consumer = new Consumer();
        when(consumerMapperForAdd.updateEntity(consumerDtoForAdd, userService)).thenReturn(consumer);
        doNothing().when(userService).save(consumer);

        consumerService.add(consumerDtoForAdd);

        verify(consumerMapperForAdd).updateEntity(consumerDtoForAdd, userService);
        verify(userService).save(consumer);
    }

    @Test
    void addToLikeBuildings() {
        Long buildingId = 1L;
        Consumer consumer = new Consumer();
        consumer.setLikeBuildings(new ArrayList<>());
        when(userService.getAuthUser()).thenReturn(consumer);
        doNothing().when(userService).save(consumer);

        consumerService.addToLikeBuildings(buildingId);

        assertTrue(consumer.getLikeBuildings().contains(buildingId));
        assertEquals(consumer.getLikeBuildings().get(0), buildingId);
        verify(userService).getAuthUser();
        verify(userService).save(consumer);
    }

    @Test
    void addToLikeFlats() {
        Long flatId = 1L;
        Consumer consumer = new Consumer();
        consumer.setLikeFlats(new ArrayList<>());
        when(userService.getAuthUser()).thenReturn(consumer);
        doNothing().when(userService).save(consumer);

        consumerService.addToLikeFlats(flatId);

        assertTrue(consumer.getLikeFlats().contains(flatId));
        assertEquals(consumer.getLikeFlats().get(0), flatId);
        verify(userService).getAuthUser();
        verify(userService).save(consumer);
    }

    @Test
    void deleteFromLikeBuildings() {
        Long buildingId = 1L;
        Consumer consumer = new Consumer();
        List<Long> likeBuilding = new ArrayList<>();
        likeBuilding.add(buildingId);
        consumer.setLikeBuildings(likeBuilding);
        when(userService.getAuthUser()).thenReturn(consumer);
        doNothing().when(userService).save(consumer);

        consumerService.deleteFromLikeBuildings(buildingId);

        assertFalse(consumer.getLikeBuildings().contains(buildingId));
        assertEquals(consumer.getLikeBuildings().size(), 0);
        verify(userService).getAuthUser();
        verify(userService).save(consumer);
    }

    @Test
    void deleteFromLikeFlats() {
        Long flatId = 1L;
        Consumer consumer = new Consumer();
        List<Long> likeFlats = new ArrayList<>();
        likeFlats.add(flatId);
        consumer.setLikeFlats(likeFlats);
        when(userService.getAuthUser()).thenReturn(consumer);
        doNothing().when(userService).save(consumer);

        consumerService.deleteFromLikeFlats(flatId);

        assertFalse(consumer.getLikeFlats().contains(flatId));
        assertEquals(consumer.getLikeFlats().size(), 0);
        verify(userService).getAuthUser();
        verify(userService).save(consumer);
    }
}