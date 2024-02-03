package com.example.banking.account.service;

import com.example.banking.account.controller.dto.request.CreateInstallmentAccountRequest;
import com.example.banking.account.controller.dto.request.CreateSavingsAccountRequest;
import com.example.banking.account.controller.dto.request.GetAccountRequest;
import com.example.banking.account.controller.dto.response.AccountResponse;
import com.example.banking.account.domain.Account;
import com.example.banking.account.domain.InstallmentAccount;
import com.example.banking.account.domain.SavingsAccount;
import com.example.banking.account.repository.AccountRepository;
import com.example.banking.account.repository.InstallmentAccountRepository;
import com.example.banking.account.repository.SavingsAccountRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

  private final AccountRepository accountRepository;
  private final SavingsAccountRepository savingsAccountRepository;
  private final InstallmentAccountRepository installmentAccountRepository;

  private final AccountNumberGenerator accountNumberGenerator;

  private static final BigDecimal INITIAL_BALANCE = BigDecimal.ZERO;
  private static final boolean INITIAL_ENABLED_STATUS = true;

  @Transactional(readOnly = true)
  public List<AccountResponse> getAccounts() {
    List<Account> accounts = accountRepository.findAll();
    return toAccountResponses(accounts);
  }

  @Transactional(readOnly = true)
  public AccountResponse getAccount(GetAccountRequest getAccountRequest) {
    Account account = findByAccountNumber(getAccountRequest.getAccountNumber());
    return AccountResponse.from(account);
  }

  public void createSavingsAccount(CreateSavingsAccountRequest createSavingsAccountRequest) {
    String accountNumber = accountNumberGenerator.generate();
    SavingsAccount savingsAccount = createSavingsAccountRequest.toEntity(accountNumber,
        INITIAL_BALANCE, INITIAL_ENABLED_STATUS);
    savingsAccountRepository.save(savingsAccount);
  }

  public void createInstallmentAccount(
      CreateInstallmentAccountRequest createInstallmentAccountRequest) {
    String accountNumber = accountNumberGenerator.generate();
    InstallmentAccount installmentAccount = createInstallmentAccountRequest.toEntity(accountNumber,
        INITIAL_BALANCE, INITIAL_ENABLED_STATUS, LocalDateTime.now()
    );
    installmentAccountRepository.save(installmentAccount);
  }

  private List<AccountResponse> toAccountResponses(List<Account> accounts) {
    List<AccountResponse> accountResponses = new ArrayList<>();
    accounts.forEach(account -> accountResponses.add(AccountResponse.from(account)));
    return accountResponses;
  }

  public Account findByAccountNumber(String accountNumber) {
    return accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계좌입니다."));
  }
}
