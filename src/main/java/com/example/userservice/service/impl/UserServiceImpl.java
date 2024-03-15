package com.example.userservice.service.impl;

import com.example.userservice.entity.enums.StatusUser;
import com.example.userservice.entity.users.Admin;
import com.example.userservice.entity.users.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public boolean validPhone(String telephone) {
        log.info("UserServiceImpl-validTelephone start");
        boolean res = userRepository.existsByPhone(telephone);
        log.info("UserServiceImpl-validTelephone successfully");
        return res;
    }
    @Override
    public boolean validEmail(String email) {
        log.info("UserServiceImpl-validEmail start");
        boolean res = userRepository.existsByEmail(email);
        log.info("UserServiceImpl-validEmail successfully");
        return res;
    }
    @Override
    public User getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> {
                    log.error("User with id={} not found", id);
                    return new EntityNotFoundException("User with id="+id+" not found");
                }
        );
        return user;
    }
    @Transactional
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                ()-> {
                    log.error("User with email={} not found", email);
                    return new EntityNotFoundException("User with email="+email+" not found");
                }
        );
    }

    @Override
    public void deleteById(Long id) {
        User user = getById(id);
        user.setStatus(StatusUser.REMOVE);
        save(user);
    }

}
