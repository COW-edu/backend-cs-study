package com.example.banking.account.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("SavingsAccount")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "savings_account_id"))
public class SavingsAccount extends Account{

  @Builder
  public SavingsAccount(String accountNumber, String owner, BigDecimal balance, boolean isEnabled) {
    super(accountNumber, owner, balance, isEnabled);
  }
}
