package com.example.userservice.jwt;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}