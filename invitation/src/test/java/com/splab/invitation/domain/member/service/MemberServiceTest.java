package com.splab.invitation.domain.member.service;

import com.splab.invitation.domain.member.entity.InvitationStatus;
import com.splab.invitation.domain.member.entity.Member;
import com.splab.invitation.domain.member.service.dto.InviteMemberCommand;
import com.splab.invitation.persistence.member.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({MemberService.class})
public class MemberServiceTest {

  @Autowired
  MemberService memberService;
  @Autowired
  MemberRepository repository;
  @MockBean
  EmailInvitationService emailInvitationService;

  private InviteMemberCommand command;
  private String testEmail;

  @BeforeEach
  void setUp() {
    String testName = "고건";
    String testPhone = "010-3372-4052";
    this.testEmail = "rhrjs0131@naver.com";
    String testContent = "content";

    this.command = new InviteMemberCommand(testName, testPhone, testEmail,
        testContent);
  }

  @Test
  @DisplayName("정상적인 값이 입력되면 멤버가 생성된다.")
  void task1() {
    // given
    // when
    Long memberId = memberService.create(command);

    // then
    assertThat(repository.getReferenceById(memberId)).isNotNull();
  }

  @Test
  @DisplayName("정상적으로 생성된 멤버는 PENDING 상태이다.")
  void task2() {
    // given
    // when
    Long memberId = memberService.create(command);

    // then
    assertThat(repository.getReferenceById(memberId).getStatus()).isEqualTo(
        InvitationStatus.PENDING);
  }

  @Test
  @DisplayName("PENDING 멤버가 알맞은 인증코드를 입력하면 ACTIVATED 상태로 전환된다.")
  void task3() {
    // given
    Long memberId = memberService.create(command);

    Member testMember = repository.getReferenceById(memberId);

    String invitationCode = "123456";
    when(emailInvitationService.verifyMember(testEmail, invitationCode)).thenReturn(true);

    // when
    memberService.join(testEmail, invitationCode);

    // then
    assertThat(testMember.getStatus()).isEqualTo(InvitationStatus.ACTIVATED);
  }

  @Test
  @DisplayName("PENDING 멤버가 잘못된 인증코드를 입력하면 예외가 발생한다.")
  void task4() {
    // given
    memberService.create(command);
    String wrongCode = "123456";

    when(emailInvitationService.verifyMember(testEmail, wrongCode)).thenReturn(false);

    // when
    Throwable throwable = catchThrowable(() -> memberService.join(testEmail, wrongCode));

    // then
    assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("초대받지 않은 멤버의 이메일로 인증하면 예외가 발생한다.")
  void task5() {
    // given
    memberService.create(command);
    String wrongEmail = "wrong@naver.com";
    String invitationCode = "123456";

    when(emailInvitationService.verifyMember(testEmail, invitationCode)).thenReturn(true);

    // when
    Throwable throwable = catchThrowable(() -> memberService.join(wrongEmail, invitationCode));

    // then
    assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
  }
}
