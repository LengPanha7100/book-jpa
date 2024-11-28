package com.example.demospring.bookjpa.service;

import com.example.demospring.bookjpa.model.Borrower;
import com.example.demospring.bookjpa.model.dto.request.BorrowerRequest;

import java.util.List;
import java.util.UUID;

public interface BorrowerService {
    List<Borrower> getAllBorrower();

    Borrower getBorrowerById(UUID id);

    Borrower createBorrower(BorrowerRequest borrowerRequest);

    Borrower updateBorrowerById(BorrowerRequest borrowerRequest, UUID id);

    void deleteBorrowerById(UUID id);
}
