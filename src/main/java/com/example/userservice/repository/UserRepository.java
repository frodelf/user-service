package com.example.userservice.repository;

import com.example.userservice.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhone(String telephone);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
