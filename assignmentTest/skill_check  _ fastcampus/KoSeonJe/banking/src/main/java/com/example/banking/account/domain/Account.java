package com.example.banking.account.domain;

import com.example.banking.bank.domain.Bank;
import com.example.banking.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Account extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String accountNumber;

  private String owner;

  private BigDecimal balance;

  private boolean isEnabled;

  @ManyToOne(fetch = FetchType.LAZY)
  private Bank bank;

  public Account(String accountNumber, String owner, BigDecimal balance, boolean isEnabled) {
    this.accountNumber = accountNumber;
    this.owner = owner;
    this.balance = balance;
    this.isEnabled = isEnabled;
  }

  public void depositBalance(Long amount) {
    this.balance = balance.add(BigDecimal.valueOf(amount));
  }

  public void withDrawBalance(Long amount) {
    this.balance = balance.subtract(BigDecimal.valueOf(amount));
  }
}
