package com.hanarae.availo.dto;

import com.hanarae.availo.domain.Transaction;

import java.time.LocalDate;

public record TransactionResponseDto(
        Long id,
        String type,
        String category,
        Integer amount,
        String description,
        LocalDate date
){
    public static TransactionResponseDto from(Transaction t){
        return new TransactionResponseDto(
                t.getId(), t.getType(), t.getCategory(), t.getAmount(), t.getDescription(), t.getDate()
        );
    }
}
