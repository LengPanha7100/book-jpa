package com.example.demospring.bookjpa.model.dto.request;

import com.example.demospring.bookjpa.model.Author;
import com.example.demospring.bookjpa.model.Book;
import com.example.demospring.bookjpa.model.Genre;
import com.example.demospring.bookjpa.model.dto.Enums.EAvailability;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    private String title;
    private String isbn;
    private Long publicationYear;
    private UUID authorId;
    private UUID genreId;

    public Book toEntity(EAvailability availability , Author author , Genre genre){
        return new Book(null,this.title,this.isbn,this.publicationYear,author,genre,availability);
    }

    public Book toEntity(EAvailability availability , Author author , Genre genre , UUID id){
        return new Book(id,this.title,this.isbn,this.publicationYear,author,genre,availability);
    }
}
