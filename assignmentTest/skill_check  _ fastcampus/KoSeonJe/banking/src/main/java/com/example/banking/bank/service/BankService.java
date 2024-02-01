package com.example.banking.bank.service;

import com.example.banking.account.controller.dto.response.AccountResponse;
import com.example.banking.account.domain.Account;
import com.example.banking.account.service.AccountService;
import com.example.banking.bank.controller.dto.request.CreateBankRequest;
import com.example.banking.bank.controller.dto.request.DepositRequest;
import com.example.banking.bank.controller.dto.request.TransferMoneyRequest;
import com.example.banking.bank.controller.dto.request.WithDrawRequest;
import com.example.banking.bank.domain.Bank;
import com.example.banking.bank.repostiory.BankRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BankService {

  private final BankRepository bankRepository;
  private final AccountService accountService;

  public void createBank(CreateBankRequest createBankRequest) {
    Bank bank = createBankRequest.toEntity();
    bankRepository.save(bank);
  }

  public AccountResponse deposit(DepositRequest depositRequest) {
    Account account = accountService.findByAccountNumber(depositRequest.getAccountNumber());
    account.depositBalance(depositRequest.getAmount());
    return AccountResponse.from(account);
  }

  public AccountResponse withDraw(WithDrawRequest withDrawRequest) {
    Account account = accountService.findByAccountNumber(withDrawRequest.getAccountNumber());
    account.withDrawBalance(withDrawRequest.getAmount());
    return AccountResponse.from(account);
  }

  public AccountResponse transferMoney(TransferMoneyRequest transferMoneyRequest) {
    Account myAccount = accountService.findByAccountNumber(transferMoneyRequest.getMyAccountNumber());
    Account transferAccount = accountService.findByAccountNumber(transferMoneyRequest.getTransferAccountNumber());
    checkBalance(myAccount.getBalance(), transferAccount.getBalance());
    myAccount.withDrawBalance(transferMoneyRequest.getAmount());
    transferAccount.depositBalance(transferMoneyRequest.getAmount());

    return AccountResponse.from(myAccount);
  }

  private void checkBalance(BigDecimal myBalance, BigDecimal transferAmount) {
    if (myBalance.compareTo(transferAmount) < 0) {
      throw new IllegalArgumentException("계좌 잔액이 부족합니다");
    }
  }
}
