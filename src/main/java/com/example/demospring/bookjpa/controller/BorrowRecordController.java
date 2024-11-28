package com.example.demospring.bookjpa.controller;

import com.example.demospring.bookjpa.model.BorrowRecord;
import com.example.demospring.bookjpa.model.BorrowedBook;
import com.example.demospring.bookjpa.model.dto.Enums.EStatus;
import com.example.demospring.bookjpa.model.dto.request.BorrowRecordRequest;
import com.example.demospring.bookjpa.model.dto.response.APIResponse;
import com.example.demospring.bookjpa.service.BorrowRecordeService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/borrowRecord")
public class BorrowRecordController {
    private final BorrowRecordeService borrowRecordeService;

    public BorrowRecordController(BorrowRecordeService borrowRecordeService) {
        this.borrowRecordeService = borrowRecordeService;
    }
    @GetMapping
    public ResponseEntity<APIResponse<List<BorrowRecord>>> getAllBorrowRecord(){
        List<BorrowRecord> borrowRecords = borrowRecordeService.getAllBorrowRecord();
        APIResponse<List<BorrowRecord>> apiResponse = APIResponse.<List<BorrowRecord>>builder()
                .message("Get all borrow record successfully!")
                .status(HttpStatus.OK)
                .payload(borrowRecords)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/overdue")
    public ResponseEntity<APIResponse<List<BorrowRecord>>> getAllBorrowRecordByOverdue(){
        List<BorrowRecord> borrowRecords = borrowRecordeService.getAllBorrowRecordByOverdue();
        APIResponse<List<BorrowRecord>> apiResponse = APIResponse.<List<BorrowRecord>>builder()
                .message("Get all borrow record by overdue successfully!")
                .status(HttpStatus.OK)
                .payload(borrowRecords)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<BorrowRecord>> getBorrowRecordById(@PathVariable UUID id){
        BorrowRecord borrowRecords = borrowRecordeService.getBorrowRecordById(id);
        APIResponse<BorrowRecord> apiResponse = APIResponse.<BorrowRecord>builder()
                .message("Get borrow record by id successfully!")
                .status(HttpStatus.OK)
                .payload(borrowRecords)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<APIResponse<BorrowRecord>> createBorrowRecord(@RequestBody BorrowRecordRequest borrowRecordRequest , @RequestParam EStatus status){
        BorrowRecord borrowRecords = borrowRecordeService.createBorrowRecord(borrowRecordRequest,status);
        APIResponse<BorrowRecord> apiResponse = APIResponse.<BorrowRecord>builder()
                .message("Created borrow record successfully!")
                .status(HttpStatus.CREATED)
                .payload(borrowRecords)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    @PostMapping("/{id}/return")
    public ResponseEntity<APIResponse<BorrowRecord>> returnBook(@PathVariable UUID id){
        BorrowRecord borrowRecords = borrowRecordeService.returnBook(id);
        APIResponse<BorrowRecord> apiResponse = APIResponse.<BorrowRecord>builder()
                .message("Return book successfully!")
                .status(HttpStatus.CREATED)
                .payload(borrowRecords)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<BorrowRecord>> updateBorrowRecordById(@RequestBody BorrowRecordRequest borrowRecordRequest,@PathVariable UUID id ,  @RequestParam EStatus status){
        BorrowRecord borrowRecords = borrowRecordeService.updateBorrowRecordById(borrowRecordRequest,id,status);
        APIResponse<BorrowRecord> apiResponse = APIResponse.<BorrowRecord>builder()
                .message("Updated borrow record by id successfully!")
                .status(HttpStatus.OK)
                .payload(borrowRecords)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<BorrowRecord>> deleteBorrowRecordById(@PathVariable UUID id){
        borrowRecordeService.deleteBorrowRecordById(id);
        APIResponse<BorrowRecord> apiResponse = APIResponse.<BorrowRecord>builder()
                .message("Deleted borrow record by id successfully!")
                .status(HttpStatus.OK)
                .payload(null)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
