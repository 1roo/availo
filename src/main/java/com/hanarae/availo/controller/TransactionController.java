package com.hanarae.availo.controller;

import com.hanarae.availo.domain.Transaction;
import com.hanarae.availo.dto.ForecastResponseDto;
import com.hanarae.availo.dto.TransactionRequestDto;
import com.hanarae.availo.dto.TransactionResponseDto;
import com.hanarae.availo.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Tag(name = "Transaction API", description = "수입/지출 트랜잭션 관리 API")
public class TransactionController {
    private final TransactionService transactionService;

    @Operation(summary = "수입/지출 등록", description = "새로운 수입 또는 지출 항목을 등록.")
    @PostMapping
    public TransactionResponseDto save(@RequestBody TransactionRequestDto dto) {
        Transaction saved = transactionService.save(dto.toEntity());
        return TransactionResponseDto.from(saved);
    }

    @PostMapping("/batch")
    @Operation(summary = "여러 수입/지출 항목 일괄 등록")
    public List<TransactionResponseDto> saveAll(@RequestBody List<TransactionRequestDto> dtos) {
        return dtos.stream()
                .map(dto -> transactionService.save(dto.toEntity()))
                .map(TransactionResponseDto::from)
                .toList();
    }

    @Operation(summary = "월별 내역 조회", description = "지정한 연도와 월에 해당하는 모든 트랜잭션 내역을 조회")
    @GetMapping("/month")
    public List<Transaction> getByMonth(@RequestParam int year, @RequestParam int month) {
        return transactionService.findByMonth(year, month);
    }

    @Operation(summary = "다음 달 가용 금액 예측", description = "고정 수입과 고정 지출을 기반으로 다음 달의 사용 가능 금액을 계산")
    @GetMapping("/next-month-forecast")
    public ForecastResponseDto getForecastForNextMonth() {
        return transactionService.getForecastForNextMonth();
    }
}
