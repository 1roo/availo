package com.hanarae.availo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "다음 달 예산 예측 결과 DTO")
public record ForecastResponseDto(
    @Schema(description = "고정 수입 합계", example = "3000000")
    int fixedIncome,

    @Schema(description = "고정 지출 합계", example = "1200000")
    int fixedExpense,

    @Schema(description = "예상 사용 가능 금액", example = "1800000")
    int available
) {}
