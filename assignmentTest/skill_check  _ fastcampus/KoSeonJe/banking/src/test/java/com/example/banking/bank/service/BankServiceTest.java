package com.example.banking.bank.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.banking.account.domain.Account;
import com.example.banking.account.domain.SavingsAccount;
import com.example.banking.account.repository.AccountRepository;
import com.example.banking.bank.controller.dto.request.CreateBankRequest;
import com.example.banking.bank.domain.Bank;
import com.example.banking.bank.repostiory.BankRepository;
import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class BankServiceTest {

  @Autowired
  private BankService bankService;

  @Autowired
  private BankRepository bankRepository;

  @Autowired
  private AccountRepository accountRepository;

  @DisplayName("은행을 생성하기 위한 정보를 이용해 생성한뒤 저장한다.")
  @Test
  void createBank() {
    // given
    CreateBankRequest createBankRequest = new CreateBankRequest("카우", "이자율 정책");
    // when
    bankService.createBank(createBankRequest);
    Bank bank = bankRepository.findByName("카우").orElseThrow(IllegalArgumentException::new);
    // then
    assertThat(bank).isNotNull();
    assertThat(bank.getName()).isEqualTo("카우");
    assertThat(bank.getInterestPolicies().getPolicyName()).isEqualTo("이자율 정책");
  }

  @DisplayName("계좌에 돈을 입금하면 잔고에 입금한 금액만큼 더해진다")
  @Test
  void deposit() {
    // given
    SavingsAccount savingsAccount = SavingsAccount.builder()
        .accountNumber("123")
        .owner("사람")
        .balance(BigDecimal.ZERO)
        .build();
    accountRepository.save(savingsAccount);
    Account account = accountRepository.findByAccountNumber("123").orElseThrow(IllegalArgumentException::new);
    // when
    account.depositBalance(11111L);
    // then
    Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(11111L));
  }
}