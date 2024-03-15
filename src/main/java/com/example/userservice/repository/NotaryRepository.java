package com.example.userservice.repository;

import com.example.userservice.entity.users.Notary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaryRepository extends JpaRepository<Notary, Long> {
}