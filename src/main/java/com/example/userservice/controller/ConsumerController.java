package com.example.userservice.controller;

import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.entity.enums.StatusUser;
import com.example.userservice.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/v1/consumer")
@RequiredArgsConstructor
public class ConsumerController {
    private final ConsumerService consumerService;
    @GetMapping("/get-all")
    public ResponseEntity<Page<UserDtoForViewAll>> getAll(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam String consumerName){
        return ResponseEntity.ok(consumerService.getAll(page, pageSize, consumerName, StatusUser.ACTIVE));
    }
    @GetMapping("/get-all-blocked")
    public ResponseEntity<Page<UserDtoForViewAll>> getAllBlocked(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam String consumerName){
        return ResponseEntity.ok(consumerService.getAll(page, pageSize, consumerName, StatusUser.REMOVE));
    }
    @PutMapping("/change-status/{consumerId}")
    public ResponseEntity<String> changeStatusForFlat(@PathVariable Long consumerId, @RequestParam StatusUser statusUser){
        consumerService.changeStatusById(consumerId, statusUser);
        return ResponseEntity.ok("changed");
    }
}