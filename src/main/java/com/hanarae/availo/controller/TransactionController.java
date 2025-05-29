package com.hanarae.availo.controller;

import com.hanarae.availo.domain.Transaction;
import com.hanarae.availo.dto.TransactionRequestDto;
import com.hanarae.availo.dto.TransactionResponseDto;
import com.hanarae.availo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public TransactionResponseDto save(@RequestBody TransactionRequestDto dto) {
        Transaction saved = transactionService.save(dto.toEntity());
        return TransactionResponseDto.from(saved);
    }

    @GetMapping("/month")
    public List<Transaction> getByMonth(@RequestParam int year, @RequestParam int month) {
        return transactionService.findByMonth(year, month);
    }

    @GetMapping("/next-month-available")
    public int getAvailableMoneyForNextMonth() {
        return transactionService.calculateAvailableAmountForNextMonth();
    }
}
