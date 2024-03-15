package com.example.userservice.repository;

import com.example.userservice.entity.enums.StatusUser;
import com.example.userservice.entity.users.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    Page<Consumer> findAllByNameLikeAndStatus(String name, StatusUser status, Pageable pageable);
}
