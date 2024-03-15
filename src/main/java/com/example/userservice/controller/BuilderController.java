package com.example.userservice.controller;

import com.example.userservice.dto.user.UserDtoForAdd;
import com.example.userservice.service.BuilderService;
import com.example.userservice.validator.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/v1/builder")
@RequiredArgsConstructor
public class BuilderController {
    private final UserValidator userValidator;
    private final BuilderService builderService;
    @PostMapping(value = "/add")
    public ResponseEntity<Map<String, String>> add(@ModelAttribute @Valid UserDtoForAdd userDtoForAdd, BindingResult bindingResult) throws IOException {
        userValidator.validate(userDtoForAdd, bindingResult);
        Map<String, String> errorsMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error -> errorsMap.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMap);
        }
        builderService.add(userDtoForAdd);
        return ResponseEntity.ok(Collections.singletonMap("status", "saved"));
    }
}