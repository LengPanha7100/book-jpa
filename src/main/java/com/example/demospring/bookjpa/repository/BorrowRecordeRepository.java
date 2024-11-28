package com.example.demospring.bookjpa.repository;

import com.example.demospring.bookjpa.model.BorrowRecord;
import com.example.demospring.bookjpa.model.dto.Enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BorrowRecordeRepository extends JpaRepository<BorrowRecord , UUID> {
    List<BorrowRecord> findAllByStatus (EStatus status);
}
