package com.example.banking.account.controller.dto.request;

import com.example.banking.account.domain.SavingsAccount;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSavingsAccountRequest {

  private String owner;

  public SavingsAccount toEntity(String accountNumber, BigDecimal balance, boolean isEnabled) {
    return SavingsAccount.builder()
        .accountNumber(accountNumber)
        .owner(owner)
        .balance(balance)
        .isEnabled(isEnabled)
        .build();
  }
}
