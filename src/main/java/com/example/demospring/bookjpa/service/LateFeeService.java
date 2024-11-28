package com.example.demospring.bookjpa.service;

import com.example.demospring.bookjpa.model.LateFee;
import com.example.demospring.bookjpa.model.dto.request.LateFeeRequest;

import java.util.List;
import java.util.UUID;

public interface LateFeeService {
    List<LateFee> getAllLateFee();

    LateFee getLateFeeById(UUID id);

    LateFee createLateFees(LateFeeRequest lateFeeRequest);

    LateFee updateLateFeeById(LateFeeRequest lateFeeRequest, UUID id);

    void deleteLateFeeById(UUID id);
}
