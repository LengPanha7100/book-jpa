package com.example.demospring.bookjpa.service.serviceImp;

import com.example.demospring.bookjpa.exception.NotFoundException;
import com.example.demospring.bookjpa.model.Book;
import com.example.demospring.bookjpa.model.BorrowRecord;
import com.example.demospring.bookjpa.model.Borrower;
import com.example.demospring.bookjpa.model.dto.Enums.EStatus;
import com.example.demospring.bookjpa.model.dto.request.BorrowRecordRequest;
import com.example.demospring.bookjpa.repository.BorrowRecordeRepository;
import com.example.demospring.bookjpa.repository.BorrowerBookRepository;
import com.example.demospring.bookjpa.service.BookService;
import com.example.demospring.bookjpa.service.BorrowRecordeService;
import com.example.demospring.bookjpa.service.BorrowerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BorrowRecordeServiceImp implements BorrowRecordeService {
    private final BorrowRecordeRepository borrowRecordeRepository;
    private final BookService bookService;
    private final BorrowerService borrowerService;

    public BorrowRecordeServiceImp(BorrowRecordeRepository borrowRecordeRepository, BookService bookService, BorrowerService borrowerService) {
        this.borrowRecordeRepository = borrowRecordeRepository;

        this.bookService = bookService;
        this.borrowerService = borrowerService;
    }

    @Override
    public List<BorrowRecord> getAllBorrowRecord() {
        return borrowRecordeRepository.findAll();
    }

    @Override
    public BorrowRecord getBorrowRecordById(UUID id) {
        BorrowRecord borrowRecord = borrowRecordeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Borrow record by id "+id+" not found")
        );
        return borrowRecord;
    }

    @Override
    public BorrowRecord createBorrowRecord(BorrowRecordRequest borrowRecordRequest , EStatus status) {
        Book book = bookService.getBookById(borrowRecordRequest.getBookId());
        Borrower borrower = borrowerService.getBorrowerById(borrowRecordRequest.getBorrowerId());
        return borrowRecordeRepository.save(borrowRecordRequest.toEntity(status,book,borrower));
    }

    @Override
    public BorrowRecord updateBorrowRecordById(BorrowRecordRequest borrowRecordRequest, UUID id ,EStatus status) {
        getBorrowRecordById(id);
        Book book = bookService.getBookById(borrowRecordRequest.getBookId());
        Borrower borrower = borrowerService.getBorrowerById(borrowRecordRequest.getBorrowerId());
        return borrowRecordeRepository.save(borrowRecordRequest.toEntity(status,book,borrower,id));
    }

    @Override
    public void deleteBorrowRecordById(UUID id) {
        getBorrowRecordById(id);
        borrowRecordeRepository.deleteById(id);
    }

    @Override
    public List<BorrowRecord> getAllBorrowRecordByOverdue() {
        return borrowRecordeRepository.findAllByStatus(EStatus.OVERDUE);
    }

    @Override
    public BorrowRecord returnBook( UUID id) {
        BorrowRecord borrowRecord = getBorrowRecordById(id);
        borrowRecord.setStatus(EStatus.RETURNED);
        return borrowRecordeRepository.save(borrowRecord);
    }
}
