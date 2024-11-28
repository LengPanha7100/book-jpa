package com.example.demospring.bookjpa.controller;

import com.example.demospring.bookjpa.model.Author;
import com.example.demospring.bookjpa.model.dto.request.AuthorRequest;
import com.example.demospring.bookjpa.model.dto.response.APIResponse;
import com.example.demospring.bookjpa.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping
    public ResponseEntity<APIResponse<List<Author>>> getAllAuthor(){
        List<Author> authors = authorService.getAllAuthor();
        APIResponse<List<Author>> apiResponse = APIResponse.<List<Author>>builder()
                .message("Get all author successfully")
                .status(HttpStatus.OK)
                .payload(authors)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Author>> getAuthorById (@PathVariable UUID id){
        Author authors = authorService.getAuthorById(id);
        APIResponse<Author> apiResponse = APIResponse.<Author>builder()
                .message("Get author by id successfully")
                .status(HttpStatus.OK)
                .payload(authors)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<APIResponse<Author>> createAuthor (@RequestBody AuthorRequest authorRequest){
        Author authors = authorService.createAuthor(authorRequest);
        APIResponse<Author> apiResponse = APIResponse.<Author>builder()
                .message("Create author successfully")
                .status(HttpStatus.CREATED)
                .payload(authors)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Author>> updateAuthorById (@PathVariable UUID id , @RequestBody AuthorRequest authorRequest ){
        Author authors = authorService.updateAuthorById(id , authorRequest);
        APIResponse<Author> apiResponse = APIResponse.<Author>builder()
                .message("Updated author by id successfully")
                .status(HttpStatus.OK)
                .payload(authors)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Author>> deleteAuthorById (@PathVariable UUID id ){
        authorService.deleteAuthorById(id);
        APIResponse<Author> apiResponse = APIResponse.<Author>builder()
                .message("Deleted author by id successfully")
                .status(HttpStatus.OK)
                .payload(null)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
