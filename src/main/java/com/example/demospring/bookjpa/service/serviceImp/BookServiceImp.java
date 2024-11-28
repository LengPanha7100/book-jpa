package com.example.demospring.bookjpa.service.serviceImp;

import com.example.demospring.bookjpa.exception.NotFoundException;
import com.example.demospring.bookjpa.model.*;
import com.example.demospring.bookjpa.model.dto.Enums.EAvailability;
import com.example.demospring.bookjpa.model.dto.request.BookRequest;
import com.example.demospring.bookjpa.repository.BookRepository;
import com.example.demospring.bookjpa.repository.BorrowerBookRepository;
import com.example.demospring.bookjpa.repository.BorrowerRepository;
import com.example.demospring.bookjpa.service.AuthorService;
import com.example.demospring.bookjpa.service.BookService;
import com.example.demospring.bookjpa.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImp implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BorrowerRepository borrowerRepository;
    private final BorrowerBookRepository borrowerBookRepository;


    public BookServiceImp(BookRepository bookRepository, AuthorService authorService, GenreService genreService, BorrowerRepository borrowerRepository, BorrowerBookRepository borrowerBookRepository) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
        this.borrowerRepository = borrowerRepository;
        this.borrowerBookRepository = borrowerBookRepository;
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll() ;
    }

    @Override
    public Book getBookById(UUID id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book by id "+id+" not found")
        );
        return book;
    }

    @Override
    public Book createBook(BookRequest bookRequest , EAvailability availability) {
        Author author = authorService.getAuthorById(bookRequest.getAuthorId());
        Genre genre = genreService.getGenreById(bookRequest.getGenreId());
        Book book = bookRepository.save(bookRequest.toEntity(availability,author,genre));
        return book;
    }

    @Override
    public Book updateBookById(BookRequest bookRequest, UUID id , EAvailability availability) {
        getBookById(id);
        Author author = authorService.getAuthorById(bookRequest.getAuthorId());
        Genre genre = genreService.getGenreById(bookRequest.getGenreId());
        return bookRepository.save(bookRequest.toEntity(availability,author,genre,id));
    }

    @Override
    public void deleteBookById(UUID id) {
        getBookById(id);
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getAllBookByGenreId(UUID genreId) {
        return bookRepository.findAllByGenreId(genreId);
    }

    @Override
    public List<Book> getAllBookByAuthorId(UUID authorId) {
        return bookRepository.findAllByAuthorId(authorId);
    }

    @Override
    public List<Book> getAllBookByAvailable() {
        return bookRepository.findAllByAvailability(EAvailability.TRUE);
    }

    @Override
    public void createBookByIdAndBorrowerId(UUID id, UUID borrowerId) {
        Borrower borrower = borrowerRepository.findById(borrowerId).orElseThrow(
                () -> new NotFoundException("Borrower by id "+borrowerId+ " not found")
        );
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book by id "+id+" not found")
        );
        BorrowedBook borrowedBook = new BorrowedBook(null,book,borrower);
        borrowerBookRepository.save(borrowedBook);
    }
}
