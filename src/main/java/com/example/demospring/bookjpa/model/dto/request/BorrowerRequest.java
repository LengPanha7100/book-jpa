package com.example.demospring.bookjpa.model.dto.request;

import com.example.demospring.bookjpa.model.Borrower;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowerRequest {
    private String name;
    private String email;
    private String phoneNumber;

    public Borrower toEntity(){
        return new Borrower(null,this.name,this.email,this.phoneNumber);
    }
    public Borrower toEntity(UUID id){
        return new Borrower(id,this.name,this.email,this.phoneNumber);
    }
}
