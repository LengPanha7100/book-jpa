package com.example.demospring.bookjpa.repository;

import com.example.demospring.bookjpa.model.Author;
import com.example.demospring.bookjpa.model.Book;
import com.example.demospring.bookjpa.model.Genre;
import com.example.demospring.bookjpa.model.dto.Enums.EAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findAllByGenreId (UUID genreId);

    List<Book> findAllByAuthorId (UUID authorId);

    List<Book> findAllByAvailability (EAvailability availability);

}
