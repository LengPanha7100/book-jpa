package com.example.demospring.bookjpa.service;

import com.example.demospring.bookjpa.model.Author;
import com.example.demospring.bookjpa.model.dto.request.AuthorRequest;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    List<Author> getAllAuthor();

    Author getAuthorById(UUID id);

    Author createAuthor(AuthorRequest authorRequest);

    Author updateAuthorById(UUID id, AuthorRequest authorRequest);

    void deleteAuthorById(UUID id);
}
