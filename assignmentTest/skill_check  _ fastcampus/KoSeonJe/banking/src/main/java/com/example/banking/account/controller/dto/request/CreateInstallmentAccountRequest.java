package com.example.banking.account.controller.dto.request;

import com.example.banking.account.domain.InstallmentAccount;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateInstallmentAccountRequest {

  private String owner;

  private int month;

  private Long goalAmount;

  public InstallmentAccount toEntity(String accountNumber, BigDecimal initialBalance,
      boolean initialEnabledStatus, LocalDateTime now) {
    return InstallmentAccount.builder()
        .owner(owner)
        .accountNumber(accountNumber)
        .balance(initialBalance)
        .isEnabled(initialEnabledStatus)
        .expiration(now.plusMonths(month))
        .goalAmount(BigDecimal.valueOf(goalAmount))
        .build();
  }
}
