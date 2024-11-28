package com.example.demospring.bookjpa.model.dto.request;

import com.example.demospring.bookjpa.model.Book;
import com.example.demospring.bookjpa.model.BorrowRecord;
import com.example.demospring.bookjpa.model.Borrower;
import com.example.demospring.bookjpa.model.dto.Enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowRecordRequest {
    private UUID bookId;
    private UUID borrowerId;
    private LocalDate borrowDate;
    private LocalDate expectedReturnDate;

    public BorrowRecord toEntity(EStatus status, Book book , Borrower borrower){
        return new BorrowRecord(null,status,book,borrower,this.borrowDate,this.expectedReturnDate,null);
    }

    public BorrowRecord toEntity(EStatus status, Book book , Borrower borrower,UUID id){
        return new BorrowRecord(id,status,book,borrower,this.borrowDate,this.expectedReturnDate,null);
    }

}
