package com.example.demospring.bookjpa.controller;

import com.example.demospring.bookjpa.model.Borrower;
import com.example.demospring.bookjpa.model.dto.request.BorrowerRequest;
import com.example.demospring.bookjpa.model.dto.response.APIResponse;
import com.example.demospring.bookjpa.service.BorrowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/borrowers")
public class BorrowerController {
    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }
    @GetMapping
    public ResponseEntity<APIResponse<List<Borrower>>> getAllBorrower(){
        List<Borrower> borrower = borrowerService.getAllBorrower();
        APIResponse<List<Borrower>> apiResponse = APIResponse.<List<Borrower>>builder()
                .message("Get all borrower successfully!")
                .status(HttpStatus.OK)
                .payload(borrower)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Borrower>> getBorrowerById(@PathVariable UUID id){
      Borrower borrower = borrowerService.getBorrowerById(id);
        APIResponse<Borrower> apiResponse = APIResponse.<Borrower>builder()
                .message("Get borrower by id successfully!")
                .status(HttpStatus.OK)
                .payload(borrower)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Borrower>> createBorrower(@RequestBody BorrowerRequest borrowerRequest){
        Borrower borrower = borrowerService.createBorrower(borrowerRequest);
        APIResponse<Borrower> apiResponse = APIResponse.<Borrower>builder()
                .message("Created borrower successfully!")
                .status(HttpStatus.CREATED)
                .payload(borrower)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Borrower>> updateBorrowerById(@RequestBody BorrowerRequest borrowerRequest , @PathVariable UUID id){
        Borrower borrower = borrowerService.updateBorrowerById(borrowerRequest,id);
        APIResponse<Borrower> apiResponse = APIResponse.<Borrower>builder()
                .message("Updated borrower by id successfully!")
                .status(HttpStatus.OK)
                .payload(borrower)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Borrower>> deleteBorrowerById(@PathVariable UUID id){
        borrowerService.deleteBorrowerById(id);
        APIResponse<Borrower> apiResponse = APIResponse.<Borrower>builder()
                .message("Deleted borrower by id successfully!")
                .status(HttpStatus.OK)
                .payload(null)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
