package com.hanarae.availo.dto;

import com.hanarae.availo.domain.Transaction;

import java.time.LocalDate;

public record TransactionRequestDto (
    String type,
    String category,
    Integer amount,
    String description,
    LocalDate date
) {
    public Transaction toEntity() {
        return Transaction.builder()
                .type(type)
                .category(category)
                .amount(amount)
                .description(description)
                .date(date)
                .build();
    }
}
