package com.example.demospring.bookjpa.model.dto.request;
import com.example.demospring.bookjpa.model.BorrowRecord;
import com.example.demospring.bookjpa.model.LateFee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LateFeeRequest {
    private UUID borrowRecordId;
    private BigDecimal lateFee;

    public LateFee toEntity(BorrowRecord borrowRecord){
        return new LateFee(null,borrowRecord,this.lateFee);
    }
    public LateFee toEntity(BorrowRecord borrowRecord , UUID id){
        return new LateFee(id,borrowRecord,this.lateFee);
    }
}
