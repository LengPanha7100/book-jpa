package com.example.demospring.bookjpa.controller;

import com.example.demospring.bookjpa.model.LateFee;
import com.example.demospring.bookjpa.model.dto.request.LateFeeRequest;
import com.example.demospring.bookjpa.model.dto.response.APIResponse;
import com.example.demospring.bookjpa.service.LateFeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/lateFees")
public class LateFeeController {
    private final LateFeeService lateFeeService;

    public LateFeeController(LateFeeService lateFeeService) {
        this.lateFeeService = lateFeeService;
    }
    @GetMapping
    public ResponseEntity<APIResponse<List<LateFee>>> getAllLateFee(){
        List<LateFee> lateFees = lateFeeService.getAllLateFee();
        APIResponse<List<LateFee>> apiResponse = APIResponse.<List<LateFee>>builder()
                .message("Get all lateFees successfully!")
                .status(HttpStatus.OK)
                .payload(lateFees)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<LateFee>> getLateFeeById(@PathVariable UUID id){
        LateFee lateFees = lateFeeService.getLateFeeById(id);
        APIResponse<LateFee> apiResponse = APIResponse.<LateFee>builder()
                .message("Get lateFee by id successfully!")
                .status(HttpStatus.OK)
                .payload(lateFees)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<APIResponse<LateFee>> createLateFees(@RequestBody LateFeeRequest lateFeeRequest){
        LateFee lateFees = lateFeeService.createLateFees(lateFeeRequest);
        APIResponse<LateFee> apiResponse = APIResponse.<LateFee>builder()
                .message("Create lateFee successfully!")
                .status(HttpStatus.CREATED)
                .payload(lateFees)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<LateFee>> updateLateFeeById(@RequestBody LateFeeRequest lateFeeRequest,@PathVariable UUID id){
        LateFee lateFees = lateFeeService.updateLateFeeById(lateFeeRequest,id);
        APIResponse<LateFee> apiResponse = APIResponse.<LateFee>builder()
                .message("Update lateFee by id successfully!")
                .status(HttpStatus.OK)
                .payload(lateFees)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<LateFee>> deleteLateFeeById(@PathVariable UUID id){
        lateFeeService.deleteLateFeeById(id);
        APIResponse<LateFee> apiResponse = APIResponse.<LateFee>builder()
                .message("Deleted lateFee by id successfully!")
                .status(HttpStatus.OK)
                .payload(null)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
