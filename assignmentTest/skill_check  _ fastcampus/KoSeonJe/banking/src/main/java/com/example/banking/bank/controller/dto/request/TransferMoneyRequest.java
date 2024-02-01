package com.example.banking.bank.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransferMoneyRequest {

  private String myAccountNumber;
  private String transferAccountNumber;
  private Long amount;
}
