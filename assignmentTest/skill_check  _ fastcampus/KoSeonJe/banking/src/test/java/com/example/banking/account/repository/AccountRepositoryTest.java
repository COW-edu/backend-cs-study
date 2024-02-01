package com.example.banking.account.repository;

import com.example.banking.account.domain.SavingsAccount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
class AccountRepositoryTest {

  @Autowired
  private AccountRepository accountRepository;

  @DisplayName("중복되는 계좌번호를 가진 계좌가 있다면 true를 반환한다.")
  @Test
  void existsByAccountNumber() {
    // given
    SavingsAccount savingsAccount = SavingsAccount.builder()
        .accountNumber("123").build();
    accountRepository.save(savingsAccount);
    // when
    boolean isExist = accountRepository.existsByAccountNumber("123");
    // then
    Assertions.assertThat(isExist).isEqualTo(true);
  }
}