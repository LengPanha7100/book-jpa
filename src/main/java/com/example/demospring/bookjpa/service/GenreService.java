package com.example.demospring.bookjpa.service;

import com.example.demospring.bookjpa.model.Genre;
import com.example.demospring.bookjpa.model.dto.request.GenreRequest;

import java.util.List;
import java.util.UUID;

public interface GenreService {
    List<Genre> getAllGenre();

    Genre getGenreById(UUID id);

    Genre createGenre(GenreRequest genreRequest);

    Genre updateGenreById(UUID id, GenreRequest genreRequest);

    void deleteGenreById(UUID id);
}
