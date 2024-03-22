package com.example.userservice.service.impl;

import com.example.userservice.entity.enums.StatusUser;
import com.example.userservice.entity.users.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        log.info("UserServiceImpl-validTelephone finish");
        return res;
    }
    @Override
    public boolean validEmail(String email) {
        log.info("UserServiceImpl-validEmail start");
        boolean res = userRepository.existsByEmail(email);
        log.info("UserServiceImpl-validEmail finish");
        return res;
    }
    @Override
    public User getById(Long id) {
        log.info("UserServiceImpl-getById start");
        User user = userRepository.findById(id).orElseThrow(
                ()-> {
                    log.error("User with id={} not found", id);
                    return new EntityNotFoundException("User with id="+id+" not found");
                }
        );
        log.info("UserServiceImpl-getById finish");
        return user;
    }
    @Transactional
    @Override
    public void save(User user) {
        log.info("UserServiceImpl-save start");
        userRepository.save(user);
        log.info("UserServiceImpl-save finish");
    }
    @Override
    public User getByEmail(String email) {
        log.info("UserServiceImpl-getByEmail start");
        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> {
                    log.error("User with email={} not found", email);
                    return new EntityNotFoundException("User with email="+email+" not found");
                }
        );
        log.info("UserServiceImpl-getByEmail finish");
        return user;
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("UserServiceImpl-deleteById start");
        User user = getById(id);
        user.setStatus(StatusUser.REMOVE);
        save(user);
        log.info("UserServiceImpl-deleteById finish");
    }
    @Override
    public User getAuthUser() {
        log.info("UserServiceImpl-getAuthUser start");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User user = getByEmail(currentUserName);
            log.info("UserServiceImpl-getAuthUser finish");
            return user;
        }
        else {
            log.info("UserServiceImpl-getAuthUser error");
            throw new RuntimeException("The manager is not authorized");
        }
    }
}