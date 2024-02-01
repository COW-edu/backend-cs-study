package com.example.banking.bank.repostiory;

import static org.junit.jupiter.api.Assertions.*;

import com.example.banking.bank.domain.Bank;
import com.example.banking.bank.domain.interest.InterestPolicies;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
class BankRepositoryTest {

  @Autowired
  private BankRepository bankRepository;

  @DisplayName("이름을 사용하여 은행을 찾는다.")
  @Test
  void findBankByName() {
    // given
    Bank bank = Bank.builder()
        .name("카우")
        .interestPolicies(InterestPolicies.findPolicy("이자율 정책"))
        .build();
    bankRepository.save(bank);

    // when
    Bank findBank = bankRepository.findByName("카우").orElseThrow(IllegalArgumentException::new);

    // then
    Assertions.assertThat(bank).isSameAs(findBank);
  }

  @DisplayName("찾는 이름에 해당하는 은행이 없을 시 예외를 발생시킨다.")
  @Test
  void findBankByNameException() {
    // given
    Bank bank = Bank.builder()
        .name("카우")
        .interestPolicies(InterestPolicies.findPolicy("이자율 정책"))
        .build();
    bankRepository.save(bank);

    // when// then
    Assertions
        .assertThatThrownBy(() -> bankRepository.findByName("카우아님").orElseThrow(IllegalArgumentException::new))
        .isInstanceOf(IllegalArgumentException.class);
  }
}