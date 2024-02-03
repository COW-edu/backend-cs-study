package com.example.banking.account.service;

import com.example.banking.account.domain.Account;
import com.example.banking.account.repository.AccountRepository;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountNumberGenerator {

  private static final int ACCOUNT_NUMBER_LENGTH = 12;

  private static final int RANDOM_NUMBER_RANGE = 10;

  private final AccountRepository accountRepository;
  public String generate() {
    Random random = new Random();
    StringBuilder accountNumber = new StringBuilder(ACCOUNT_NUMBER_LENGTH);

    for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
      int number = random.nextInt(RANDOM_NUMBER_RANGE);
      accountNumber.append(number);
    }
    if(isExistAccountNumber(accountNumber.toString())){
      return generate();
    }
    return accountNumber.toString();
  }

  private boolean isExistAccountNumber(String accountNumber) {
    return accountRepository.existsByAccountNumber(accountNumber);
  }
}
