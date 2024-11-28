package com.example.demospring.bookjpa.controller;

import com.example.demospring.bookjpa.model.Genre;
import com.example.demospring.bookjpa.model.dto.request.GenreRequest;
import com.example.demospring.bookjpa.model.dto.response.APIResponse;
import com.example.demospring.bookjpa.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/genres")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Genre>>> getAllGenre(){
        List<Genre> genres = genreService.getAllGenre();
        APIResponse<List<Genre>> apiResponse = APIResponse.<List<Genre>>builder()
                .message("Get all genre successfully!")
                .status(HttpStatus.OK)
                .payload(genres)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Genre>> getGenreById(@PathVariable UUID id){
        Genre genres = genreService.getGenreById(id);
        APIResponse<Genre> apiResponse = APIResponse.<Genre>builder()
                .message("Get genre by id successfully!")
                .status(HttpStatus.OK)
                .payload(genres)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Genre>> createGenre(@RequestBody GenreRequest genreRequest){
        Genre genres = genreService.createGenre(genreRequest);
        APIResponse<Genre> apiResponse = APIResponse.<Genre>builder()
                .message("Created genre successfully!")
                .status(HttpStatus.CREATED)
                .payload(genres)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Genre>> updateGenreById(@PathVariable UUID id ,@RequestBody GenreRequest genreRequest){
        Genre genres = genreService.updateGenreById(id,genreRequest);
        APIResponse<Genre> apiResponse = APIResponse.<Genre>builder()
                .message("Updated genre by id successfully!")
                .status(HttpStatus.OK)
                .payload(genres)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Genre>> deleteGenreById(@PathVariable UUID id){
        genreService.deleteGenreById(id);
        APIResponse<Genre> apiResponse = APIResponse.<Genre>builder()
                .message("Deleted genre by id successfully!")
                .status(HttpStatus.OK)
                .payload(null)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
