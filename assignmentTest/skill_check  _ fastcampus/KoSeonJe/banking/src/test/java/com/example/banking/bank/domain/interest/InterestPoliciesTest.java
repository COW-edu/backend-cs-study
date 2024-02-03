package com.example.banking.bank.domain.interest;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InterestPoliciesTest {

  @DisplayName("정책 이름을 이용하여 관련된 Enum 상수를 찾는다.")
  @Test
  void findPolicyByName() {
    // given
    String policyName = "이자율 정책";
    // when
    InterestPolicies interestPolicies = InterestPolicies.findPolicy(policyName);
    // then
    Assertions.assertThat(interestPolicies).isSameAs(InterestPolicies.RATE_POLICY);
  }

  @DisplayName("알맞은 정책 이름을 입력하지 않았다면 예외를 발생시킨다.")
  @Test
  void findPolicyByNameException() {
    // given
    String policyName = "이자율 정책 아님";
    // when // then
    Assertions.assertThatThrownBy(() -> InterestPolicies.findPolicy(policyName))
        .isInstanceOf(IllegalArgumentException.class);
  }
}