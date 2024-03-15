package com.example.userservice.controller;

import com.example.userservice.dto.consumer.ConsumerDtoForAdd;
import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.entity.enums.StatusUser;
import com.example.userservice.service.ConsumerService;
import com.example.userservice.validator.ConsumerValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/v1/consumer")
@RequiredArgsConstructor
public class ConsumerController {
    private final ConsumerService consumerService;
    private final ConsumerValidator consumerValidator;
    @GetMapping("/get-all")
    public ResponseEntity<Page<UserDtoForViewAll>> getAll(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam String consumerName){
        return ResponseEntity.ok(consumerService.getAll(page, pageSize, consumerName, StatusUser.ACTIVE));
    }
    @GetMapping("/get-all-blocked")
    public ResponseEntity<Page<UserDtoForViewAll>> getAllBlocked(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam String consumerName){
        return ResponseEntity.ok(consumerService.getAll(page, pageSize, consumerName, StatusUser.REMOVE));
    }
    @PutMapping("/admin/change-status/{consumerId}")
    public ResponseEntity<String> changeStatusForFlat(@PathVariable Long consumerId, @RequestParam StatusUser statusUser){
        consumerService.changeStatusById(consumerId, statusUser);
        return ResponseEntity.ok("changed");
    }
    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> add(@ModelAttribute @Valid ConsumerDtoForAdd consumerDtoForAdd, BindingResult bindingResult) throws IOException {
        consumerValidator.validate(consumerDtoForAdd, bindingResult);
        Map<String, String> errorsMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error -> errorsMap.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMap);
        }
        consumerService.add(consumerDtoForAdd);
        return ResponseEntity.ok(Collections.singletonMap("status", "saved"));
    }
}