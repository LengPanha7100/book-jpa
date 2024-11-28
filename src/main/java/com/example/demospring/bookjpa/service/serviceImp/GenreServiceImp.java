package com.example.demospring.bookjpa.service.serviceImp;

import com.example.demospring.bookjpa.exception.NotFoundException;
import com.example.demospring.bookjpa.model.Genre;
import com.example.demospring.bookjpa.model.dto.request.GenreRequest;
import com.example.demospring.bookjpa.repository.GenreRepository;
import com.example.demospring.bookjpa.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GenreServiceImp implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImp(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(UUID id) {
        Genre genre = genreRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Genre by id "+id+ " not found" )
        );
        return genre;
    }

    @Override
    public Genre createGenre(GenreRequest genreRequest) {
        return genreRepository.save(genreRequest.toEntity());
    }

    @Override
    public Genre updateGenreById(UUID id, GenreRequest genreRequest) {
        getGenreById(id);
        return genreRepository.save(genreRequest.toEntity(id));
    }

    @Override
    public void deleteGenreById(UUID id) {
        getGenreById(id);
        genreRepository.deleteById(id);
    }
}
