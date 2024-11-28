package com.example.demospring.bookjpa.service;

import com.example.demospring.bookjpa.model.BorrowRecord;
import com.example.demospring.bookjpa.model.dto.Enums.EStatus;
import com.example.demospring.bookjpa.model.dto.request.BorrowRecordRequest;

import java.util.List;
import java.util.UUID;

public interface BorrowRecordeService {
    List<BorrowRecord> getAllBorrowRecord();

    BorrowRecord getBorrowRecordById(UUID id);

    BorrowRecord createBorrowRecord(BorrowRecordRequest borrowRecordRequest , EStatus status);

    BorrowRecord updateBorrowRecordById(BorrowRecordRequest borrowRecordRequest, UUID id , EStatus status);

    void deleteBorrowRecordById(UUID id);

    List<BorrowRecord> getAllBorrowRecordByOverdue();

    BorrowRecord returnBook(UUID id);
}
