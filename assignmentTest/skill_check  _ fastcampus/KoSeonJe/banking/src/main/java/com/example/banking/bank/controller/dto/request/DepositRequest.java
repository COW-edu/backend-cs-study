package com.example.banking.bank.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepositRequest {

  private String accountNumber;

  private Long amount;
}
