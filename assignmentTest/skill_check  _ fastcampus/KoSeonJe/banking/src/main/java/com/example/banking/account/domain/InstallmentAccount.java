package com.example.banking.account.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("InstallmentAccount")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "installment_account_id"))
public class InstallmentAccount extends Account{

  private BigDecimal goalAmount;

  private LocalDateTime expiration;

  @Builder
  public InstallmentAccount(String accountNumber, String owner, BigDecimal balance,
      boolean isEnabled, BigDecimal goalAmount, LocalDateTime expiration) {
    super(accountNumber, owner, balance, isEnabled);
    this.goalAmount = goalAmount;
    this.expiration = expiration;
  }
}
