package com.example.demospring.bookjpa.repository;

import com.example.demospring.bookjpa.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID> {
}
