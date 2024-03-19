package com.example.userservice.service;

import com.example.userservice.entity.users.User;

public interface UserService {
    boolean validPhone(String telephone);
    boolean validEmail(String email);
    User getById(Long id);
    void save(User user);
    User getByEmail(String username);
    void deleteById(Long id);
    User getAuthUser();
}