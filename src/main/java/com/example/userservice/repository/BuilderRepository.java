package com.example.userservice.repository;


import com.example.userservice.entity.users.Builder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuilderRepository extends JpaRepository<Builder, Long> {
}