package com.hanarae.availo.service;

import com.hanarae.availo.domain.Transaction;
import com.hanarae.availo.dto.ForecastResponseDto;
import com.hanarae.availo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findByMonth(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return transactionRepository.findByDateBetween(start, end);
    }

    public ForecastResponseDto getForecastForNextMonth(){
        List<Transaction> fixedIncomes = transactionRepository.findByType("fixed_income");
        List<Transaction> fixedExpenses = transactionRepository.findByType("fixed_expense");

        int income = fixedIncomes.stream().mapToInt(Transaction::getAmount).sum();
        int expense = fixedExpenses.stream().mapToInt(Transaction::getAmount).sum();
        int available = income - expense;

        return new ForecastResponseDto(income, expense, available);
    }
}
