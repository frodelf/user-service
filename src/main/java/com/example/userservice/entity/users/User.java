package com.example.userservice.entity.users;

import com.example.userservice.entity.Role;
import com.example.userservice.entity.enums.StatusUser;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String password;
    private StatusUser status;
    @ManyToOne
    private Role role;}