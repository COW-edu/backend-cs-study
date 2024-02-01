package com.example.banking.account.controller.dto.request;


import com.example.banking.account.domain.SavingsAccount;
import com.example.banking.account.repository.SavingsAccountRepository;
import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class CreateSavingsAccountRequestTest {

  @Autowired
  private SavingsAccountRepository savingsAccountRepository;

  @DisplayName("CreateSavingsAccountRequest를 이용해 SavingsAccount 인스턴스를 만든다.")
  @Test
  void toEntity() {
    // given
    CreateSavingsAccountRequest createSavingsAccountRequest = new CreateSavingsAccountRequest("사람");
    // when
    SavingsAccount savingsAccount = createSavingsAccountRequest.toEntity("123", BigDecimal.ZERO,
        true);
    // then
    Assertions.assertThat(savingsAccount).isInstanceOf(SavingsAccount.class);
    Assertions.assertThat(savingsAccount.getAccountNumber()).isSameAs("123");
    Assertions.assertThat(savingsAccount.getOwner()).isSameAs("사람");

  }
}