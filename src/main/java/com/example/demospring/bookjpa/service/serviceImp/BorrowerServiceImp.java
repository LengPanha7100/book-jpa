package com.example.demospring.bookjpa.service.serviceImp;

import com.example.demospring.bookjpa.exception.NotFoundException;
import com.example.demospring.bookjpa.model.Borrower;
import com.example.demospring.bookjpa.model.dto.request.BorrowerRequest;
import com.example.demospring.bookjpa.repository.BorrowerRepository;
import com.example.demospring.bookjpa.service.BorrowerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BorrowerServiceImp implements BorrowerService {
    private final BorrowerRepository borrowerRepository;

    public BorrowerServiceImp(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public List<Borrower> getAllBorrower() {
        return borrowerRepository.findAll();
    }

    @Override
    public Borrower getBorrowerById(UUID id) {
        Borrower borrower = borrowerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Borrower by id "+id+" not found")
        );
        return borrower;
    }

    @Override
    public Borrower createBorrower(BorrowerRequest borrowerRequest) {
        return borrowerRepository.save(borrowerRequest.toEntity());
    }

    @Override
    public Borrower updateBorrowerById(BorrowerRequest borrowerRequest, UUID id) {
        getBorrowerById(id);
        return borrowerRepository.save(borrowerRequest.toEntity(id));
    }

    @Override
    public void deleteBorrowerById(UUID id) {
        getBorrowerById(id);
        borrowerRepository.deleteById(id);
    }
}
