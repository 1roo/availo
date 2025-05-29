package com.hanarae.availo.dto;

import com.hanarae.availo.domain.Transaction;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record TransactionRequestDto (
    @Schema(description = "트랜잭션 타입 (income, expense, fixed_income, fixed_expense)", example = "income")
    String type,

    @Schema(description = "카테고리", example = "식비")
    String category,

    @Schema(description = "금액", example = "10000")
    Integer amount,

    @Schema(description = "설명", example = "점심 식사")
    String description,

    @Schema(description = "날짜 (yyyy-MM-dd)", example = "2025-06-01")
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
