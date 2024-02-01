package com.example.banking.bank.domain.interest;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InterestPolicies {
  RATE_POLICY("이자율 정책"){
    InterestPolicy getInterestPolicy(){return new RatingInterestPolicy();}
  };

  private final String policyName;

  abstract InterestPolicy getInterestPolicy();

  public static InterestPolicies findPolicy(String policyName) {
    return Arrays.stream(InterestPolicies.values())
        .filter(interestPolicies -> interestPolicies.getPolicyName().equals(policyName))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 정책 입니다."));
  }
}
