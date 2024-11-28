package com.example.demospring.bookjpa.model.dto.request;

import com.example.demospring.bookjpa.model.Author;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {
    private String name;
    private LocalDate birthday;
    private String nationality;
    private String biography;

    public Author toEntity(){
        return new Author(null,this.name,this.birthday,this.nationality,this.biography);
    }

    public Author toEntity(UUID id){
        return new Author(id,this.name,this.birthday,this.nationality,this.biography);
    }
}
