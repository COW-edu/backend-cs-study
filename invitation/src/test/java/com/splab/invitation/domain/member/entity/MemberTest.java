package com.splab.invitation.domain.member.entity;

import com.splab.invitation.domain.member.service.dto.InviteMemberCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {

  @Test
  @DisplayName("정상적인 값일 경우 객체가 생성된다.")
  void task1() {
    // given
    String testName = "고건";
    String testPhone = "010-3372-4052";
    String testEmail = "rhrjs0131@naver.com";
    String testContent = "content";

    InviteMemberCommand command = new InviteMemberCommand(testName, testPhone, testEmail,
        testContent);

    // when
    Member member = command.toEntity();

    // then
    Assertions.assertThat(member).isNotNull();
  }
}
