package com.example.banking.bank.controller.dto.request;

import com.example.banking.bank.domain.Bank;
import com.example.banking.bank.domain.interest.InterestPolicies;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateBankRequest {

  private String name;

  private String policyName;

  public Bank toEntity() {
    return Bank.builder()
        .name(name)
        .interestPolicies(InterestPolicies.findPolicy(policyName))
        .build();
  }
}
