package com.example.userservice.repository;

import com.example.userservice.entity.Flat;
import com.example.userservice.entity.enums.StatusState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlatRepository extends JpaRepository<Flat, Long> {
    Page<Flat> findAllByCorpsId(Long corpsId, Pageable pageable);

    Page<Flat> findAllByStatusState(StatusState statusState, Pageable pageable);
}