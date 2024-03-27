package com.example.userservice.service.impl;

import com.example.userservice.entity.enums.StatusUser;
import com.example.userservice.entity.users.Admin;
import com.example.userservice.entity.users.User;
import com.example.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    @Test
    void validPhone() {
        String telephone = "1234567890";
        when(userRepository.existsByPhone(telephone)).thenReturn(true);

        boolean result = userService.validPhone(telephone);

        assertTrue(result);
        verify(userRepository).existsByPhone(telephone);
    }
    @Test
    void validEmail() {
        String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        boolean result = userService.validEmail(email);

        assertTrue(result);
        verify(userRepository).existsByEmail(email);
    }
    @Test
    void getById() {
        Long id = 1L;
        User expectedUser = mock(User.class);
        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(expectedUser));

        User result = userService.getById(id);

        assertEquals(expectedUser, result);
        verify(userRepository).findById(id);
    }
    @Test
    void save() {
        User user = mock(User.class);

        userService.save(user);

        verify(userRepository).save(user);
    }
    @Test
    void getByEmail() {
        String email = "test@example.com";
        User expectedUser = mock(User.class);
        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.of(expectedUser));

        User result = userService.getByEmail(email);

        assertEquals(expectedUser, result);
        verify(userRepository).findByEmail(email);
    }
    @Test
    void deleteById() {
        Long id = 1L;
        User user = new Admin();
        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user));

        userService.deleteById(id);

        assertEquals(StatusUser.REMOVE, user.getStatus());
        verify(userRepository).save(user);
    }
    @Test
    void getAuthUser() {
        String userEmail = "test@example.com";
        User expectedUser = new Admin();
        expectedUser.setEmail(userEmail);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(userEmail);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(userRepository.findByEmail(userEmail)).thenReturn(java.util.Optional.of(expectedUser));

        User result = userService.getAuthUser();

        assertEquals(expectedUser, result);
        verify(userRepository).findByEmail(userEmail);
    }
}