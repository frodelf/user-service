package com.example.userservice.controller;

import com.example.userservice.dto.consumer.ConsumerDtoForAdd;
import com.example.userservice.dto.user.UserDtoForViewAll;
import com.example.userservice.entity.enums.StatusUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
@Tag(name = "Consumer controller", description = "Consumer API")
public interface ConsumerController {
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to get all consumers with filtering by consumer name")
    ResponseEntity<Page<UserDtoForViewAll>> getAll(@Parameter(description = "Page for pagination") @RequestParam Integer page, @Parameter(description = "Page size for page numbering") @RequestParam Integer pageSize, @Parameter(description = "Consumer name for filtering") @RequestParam String consumerName);
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to get all blocked consumers with filtering by consumer name")
    ResponseEntity<Page<UserDtoForViewAll>> getAllBlocked(@Parameter(description = "Page for pagination") @RequestParam Integer page, @Parameter(description = "Page size for page numbering") @RequestParam Integer pageSize, @Parameter(description = "Consumer name for filtering") @RequestParam String consumerName);
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to change customer status by id")
    ResponseEntity<String> changeStatusForConsumer(@Parameter(description = "Consumer id by which status will be changed") @PathVariable Long consumerId, @Parameter(description = "New status for consumer") @RequestParam StatusUser statusUser);
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to add consumer")
    ResponseEntity<Map<String, String>> add(@RequestBody(description = "DTO for adding/updating to consumer") @ModelAttribute @Valid ConsumerDtoForAdd consumerDtoForAdd, BindingResult bindingResult) throws IOException;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to add building to like buildings by building id")
    ResponseEntity<String> addLikeBuilding(@Parameter(description = "Building id by which building will be added to like buildings") @PathVariable Long buildingId);
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to add flat to like flats by flat id")
    ResponseEntity<String> addLikeFlat(@Parameter(description = "Flat id by which flat will be added to like flats") @PathVariable Long flatId);
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to delete building from like buildings by building id")
    ResponseEntity<String> deleteLikeBuilding(@Parameter(description = "Building id by which building will be deleted from like buildings") @PathVariable Long buildingId);
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to delete flat from like flats by flat id")
    ResponseEntity<String> deleteLikeFlat(@Parameter(description = "Flat id by which flat will be deleted from like flats") @PathVariable Long flatId);
}