package com.example.demospring.bookjpa.service;

import com.example.demospring.bookjpa.model.Book;
import com.example.demospring.bookjpa.model.dto.Enums.EAvailability;
import com.example.demospring.bookjpa.model.dto.request.BookRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface BookService {
    List<Book> getAllBook();

    Book getBookById(UUID id);

    Book createBook(BookRequest bookRequest , EAvailability availability);

    Book updateBookById(BookRequest bookRequest, UUID id ,EAvailability availability);

    void deleteBookById(UUID id);

    List<Book> getAllBookByGenreId(UUID genreId);

    List<Book> getAllBookByAuthorId(UUID authorId);

    List<Book> getAllBookByAvailable();

    void createBookByIdAndBorrowerId(UUID id, UUID borrowerId);
}
