package com.example.demospring.bookjpa.repository;

import com.example.demospring.bookjpa.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author,UUID> {

}
