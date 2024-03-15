package com.example.userservice.controller;

import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.service.NotaryService;
import com.example.userservice.service.UserService;
import com.example.userservice.validator.NotaryValidator;
import com.example.userservice.dto.notary.NotaryDtoForAdd;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/v1/notary")
@RequiredArgsConstructor
public class NotaryController {
    private final NotaryService notaryService;
    private final UserService userService;
    private final NotaryValidator notaryValidator;
    @GetMapping("/get-all")
    public ResponseEntity<Page<UserDtoForViewAll>> getAll(@RequestParam Integer page, @RequestParam Integer pageSize){
        return ResponseEntity.ok(notaryService.getAll(page, pageSize));
    }
    @PostMapping(value = "/add")
    public ResponseEntity<Map<String, String>> add(@ModelAttribute @Valid NotaryDtoForAdd notaryDtoForAdd, BindingResult bindingResult) throws IOException {
        notaryValidator.validate(notaryDtoForAdd, bindingResult);
        Map<String, String> errorsMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error -> errorsMap.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMap);
        }
        // TODO: 13.03.2024 доробить логіку
        notaryService.add(notaryDtoForAdd);
        return ResponseEntity.ok(Collections.singletonMap("status", "saved"));
    }
    @DeleteMapping("/delete/{notaryId}")
    public ResponseEntity<String> deleteById(@PathVariable Long notaryId){
        userService.deleteById(notaryId);
        return ResponseEntity.ok("deleted");
    }
}