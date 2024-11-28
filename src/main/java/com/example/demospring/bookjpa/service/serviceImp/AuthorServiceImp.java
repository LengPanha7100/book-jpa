package com.example.demospring.bookjpa.service.serviceImp;

import com.example.demospring.bookjpa.exception.NotFoundException;
import com.example.demospring.bookjpa.model.Author;
import com.example.demospring.bookjpa.model.dto.request.AuthorRequest;
import com.example.demospring.bookjpa.repository.AuthorRepository;
import com.example.demospring.bookjpa.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorServiceImp implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImp(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(UUID id) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Author by id "+id+ " not found")
        );
        return author;
    }

    @Override
    public Author createAuthor(AuthorRequest authorRequest) {
        return authorRepository.save(authorRequest.toEntity());
    }

    @Override
    public Author updateAuthorById(UUID id, AuthorRequest authorRequest) {
        getAuthorById(id);
        return authorRepository.save(authorRequest.toEntity(id));
    }

    @Override
    public void deleteAuthorById(UUID id) {
        getAuthorById(id);
        authorRepository.deleteById(id);
    }
}
