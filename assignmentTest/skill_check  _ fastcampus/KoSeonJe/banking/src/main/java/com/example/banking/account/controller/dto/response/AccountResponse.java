package com.example.banking.account.controller.dto.response;

import com.example.banking.account.domain.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountResponse {

  private String accountNumber;
  private String owner;
  private Long balance;

  @Builder
  public AccountResponse(String accountNumber, String owner, Long balance) {
    this.accountNumber = accountNumber;
    this.owner = owner;
    this.balance = balance;
  }

  public static AccountResponse from(Account account) {
    return AccountResponse.builder()
        .accountNumber(account.getAccountNumber())
        .balance(account.getBalance().longValue())
        .owner(account.getOwner())
        .build();
  }
}
