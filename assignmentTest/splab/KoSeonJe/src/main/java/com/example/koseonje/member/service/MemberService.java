package com.example.koseonje.member.service;


import com.example.koseonje.group.domain.Group;
import com.example.koseonje.group.service.MailService;
import com.example.koseonje.link.domain.Link;
import com.example.koseonje.link.service.LinkService;
import com.example.koseonje.member.domain.InvitationStatus;
import com.example.koseonje.member.domain.Member;
import com.example.koseonje.member.repository.MemberRepository;
import com.example.koseonje.member.service.dto.InviteServiceDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  private final MailService mailService;
  private final LinkService linkService;

  @Transactional
  public void invite(InviteServiceDto inviteServiceDto) {
    Member member = inviteServiceDto.toEntity(InvitationStatus.PENDING);
    this.memberRepository.save(member);
    this.mailService.invite(inviteServiceDto.getGroupName(), member);
  }

  @Transactional
  public void join(String accessKey) {
    Link link = this.linkService.findByKey(UUID.fromString(accessKey));
    Member member = link.getMember();
    Group group = link.getGroup();
    member.joinGroup(group);
    member.activateInvitationStatus();
    link.expireStatus();
  }
}
