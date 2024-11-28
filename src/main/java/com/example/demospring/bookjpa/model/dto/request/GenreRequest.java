package com.example.demospring.bookjpa.model.dto.request;

import com.example.demospring.bookjpa.model.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreRequest {
    private String name;
    private String description;

    public Genre toEntity(){
        return new Genre(null,this.name,this.description);
    }
    public Genre toEntity(UUID id){
        return new Genre(id,this.name,this.description);
    }
}
