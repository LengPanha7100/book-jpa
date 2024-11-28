package com.example.demospring.bookjpa.controller;

import com.example.demospring.bookjpa.model.Book;
import com.example.demospring.bookjpa.model.dto.Enums.EAvailability;
import com.example.demospring.bookjpa.model.dto.request.BookRequest;
import com.example.demospring.bookjpa.model.dto.response.APIResponse;
import com.example.demospring.bookjpa.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Book>>> getAllBook(){
        List<Book> books = bookService.getAllBook();
        APIResponse<List<Book>> apiResponse = APIResponse.<List<Book>>builder()
                .message("Get all book successfully")
                .status(HttpStatus.OK)
                .payload(books)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Book>> getBookById(@PathVariable UUID id){
        Book books = bookService.getBookById(id);
        APIResponse<Book>apiResponse = APIResponse.<Book>builder()
                .message("Get book by id successfully")
                .status(HttpStatus.OK)
                .payload(books)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/genre/{genreId}")
    public ResponseEntity<APIResponse<List<Book>>> getAllBookByGenreId(@PathVariable UUID genreId){
        List<Book> books = bookService.getAllBookByGenreId(genreId);
        APIResponse<List<Book>>apiResponse = APIResponse.<List<Book>>builder()
                .message("Get all book by genre id successfully")
                .status(HttpStatus.OK)
                .payload(books)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<APIResponse<List<Book>>> getAllBookByAuthorId(@PathVariable UUID authorId){
        List<Book> books = bookService.getAllBookByAuthorId(authorId);
        APIResponse<List<Book>>apiResponse = APIResponse.<List<Book>>builder()
                .message("Get all book by author id successfully")
                .status(HttpStatus.OK)
                .payload(books)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/available")
    public ResponseEntity<APIResponse<List<Book>>> getAllBookByAvailable(){
        List<Book> books = bookService.getAllBookByAvailable();
        APIResponse<List<Book>>apiResponse = APIResponse.<List<Book>>builder()
                .message("Get all book by available successfully")
                .status(HttpStatus.OK)
                .payload(books)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Book>> createBook(@RequestBody BookRequest bookRequest , @RequestParam EAvailability availability){
        Book books = bookService.createBook(bookRequest,availability);
        APIResponse<Book>apiResponse = APIResponse.<Book>builder()
                .message("Created book successfully")
                .status(HttpStatus.CREATED)
                .payload(books)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/{id}/borrow/{borrowerId}")
    public ResponseEntity<APIResponse<Book>> createBookByIdAndBorrowerId(@PathVariable UUID id , @PathVariable UUID borrowerId){
        bookService.createBookByIdAndBorrowerId(id,borrowerId);
        APIResponse<Book>apiResponse = APIResponse.<Book>builder()
                .message("Created book by id and borrower by id successfully")
                .status(HttpStatus.CREATED)
                .payload(null)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Book>> updateBookById(@RequestBody BookRequest bookRequest , @PathVariable UUID id ,@RequestParam EAvailability availability){
        Book books = bookService.updateBookById(bookRequest,id , availability);
        APIResponse<Book>apiResponse = APIResponse.<Book>builder()
                .message("Updated book by id successfully")
                .status(HttpStatus.OK)
                .payload(books)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Book>> deleteBookById(@PathVariable UUID id){
        bookService.deleteBookById(id);
        APIResponse<Book>apiResponse = APIResponse.<Book>builder()
                .message("Deleted book by id successfully")
                .status(HttpStatus.OK)
                .payload(null)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
