package com.splab.invitation.persistence.member;

import static org.assertj.core.api.Assertions.assertThat;

import com.splab.invitation.domain.member.entity.InvitationStatus;
import com.splab.invitation.domain.member.entity.Member;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  @DisplayName("멤버 목록 조회")
  @Test
  void addMember() {
    // given
    memberRepository.save(member());

    // when
    List<Member> memberList = memberRepository.findAll();

    // then
    assertThat(memberList.size()).isEqualTo(1);
  }

  private Member member() {
    return Member.builder()
        .name("name")
        .phone("010-3372-4052")
        .email("rhrjs0131@naver.com")
        .status(InvitationStatus.ACTIVATED)
        .build();
  }
}
