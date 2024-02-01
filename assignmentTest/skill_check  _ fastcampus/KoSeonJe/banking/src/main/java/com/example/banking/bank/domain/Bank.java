package com.example.banking.bank.domain;

import com.example.banking.account.domain.Account;
import com.example.banking.bank.domain.interest.InterestPolicies;
import com.example.banking.common.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Bank extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  private String name;

  @Enumerated(EnumType.STRING)
  @NonNull
  private InterestPolicies interestPolicies;

  @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
  private List<Account> accounts = new ArrayList<>();

  @Builder
  public Bank(Long id, @NonNull String name, @NonNull InterestPolicies interestPolicies,
      List<Account> accounts) {
    this.id = id;
    this.name = name;
    this.interestPolicies = interestPolicies;
    this.accounts = accounts;
  }
}
