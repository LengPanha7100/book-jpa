package com.example.demospring.bookjpa.service.serviceImp;

import com.example.demospring.bookjpa.exception.NotFoundException;
import com.example.demospring.bookjpa.model.BorrowRecord;
import com.example.demospring.bookjpa.model.LateFee;
import com.example.demospring.bookjpa.model.dto.request.LateFeeRequest;
import com.example.demospring.bookjpa.repository.LateFeeRepository;
import com.example.demospring.bookjpa.service.BorrowRecordeService;
import com.example.demospring.bookjpa.service.BorrowerService;
import com.example.demospring.bookjpa.service.LateFeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LateFeeServiceImp implements LateFeeService {
    private final LateFeeRepository lateFeeRepository;
    private final BorrowRecordeService borrowRecordeService;

    public LateFeeServiceImp(LateFeeRepository lateFeeRepository, BorrowRecordeService borrowRecordeService) {
        this.lateFeeRepository = lateFeeRepository;

        this.borrowRecordeService = borrowRecordeService;
    }

    @Override
    public List<LateFee> getAllLateFee() {
        return lateFeeRepository.findAll();
    }

    @Override
    public LateFee getLateFeeById(UUID id) {
        LateFee lateFee = lateFeeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("LateFee by id "+id+ " not found")
        );
        return lateFee;
    }

    @Override
    public LateFee createLateFees(LateFeeRequest lateFeeRequest) {
        BorrowRecord borrowRecord = borrowRecordeService.getBorrowRecordById(lateFeeRequest.getBorrowRecordId());
        return lateFeeRepository.save(lateFeeRequest.toEntity(borrowRecord));
    }

    @Override
    public LateFee updateLateFeeById(LateFeeRequest lateFeeRequest, UUID id) {
        getLateFeeById(id);
        BorrowRecord borrowRecord = borrowRecordeService.getBorrowRecordById(lateFeeRequest.getBorrowRecordId());
        return lateFeeRepository.save(lateFeeRequest.toEntity(borrowRecord,id));
    }

    @Override
    public void deleteLateFeeById(UUID id) {
        getLateFeeById(id);
        lateFeeRepository.deleteById(id);
    }
}
