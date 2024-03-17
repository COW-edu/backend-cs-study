package com.splab.invitation.domain.member.service;

import com.splab.invitation.domain.member.entity.Member;
import com.splab.invitation.domain.member.service.dto.InviteMemberCommand;
import com.splab.invitation.persistence.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

  private final EmailInvitationService emailInvitationService;
  private final MemberRepository repository;

  public Long create(InviteMemberCommand command) {
    Member member = command.toEntity();

    return repository.save(member).getId();
  }

  public void join(String email, String code) {

    if (!emailInvitationService.verifyMember(email, code)) {
      throw new IllegalArgumentException("인증 코드가 올바르지 않습니다.");
    }

    Member member = repository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 사용자가 존재하지 않습니다."));

    member.activate();
  }
}
