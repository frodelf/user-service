package com.example.userservice.controller;

import com.example.userservice.dto.user.UserDtoForAdd;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.util.Map;

@Tag(name = "Builder controller", description = "Builder API")
public interface BuilderController {
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to add builder")
    ResponseEntity<Map<String, String>> add(@RequestBody(description = "DTO for adding/updating builder") @ModelAttribute @Valid UserDtoForAdd userDtoForAdd, BindingResult bindingResult) throws IOException ;
}