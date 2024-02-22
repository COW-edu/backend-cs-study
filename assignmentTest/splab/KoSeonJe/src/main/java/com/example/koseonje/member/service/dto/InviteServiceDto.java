package com.example.koseonje.member.service.dto;

import com.example.koseonje.member.domain.InvitationStatus;
import com.example.koseonje.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InviteServiceDto {
  private String groupName;
  private String memberName;
  private String phoneNumber;
  private String email;

  @Builder
  private InviteServiceDto(String groupName, String memberName, String phoneNumber, String email) {
    this.groupName = groupName;
    this.memberName = memberName;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public Member toEntity(InvitationStatus status) {
    return Member.builder()
        .name(memberName)
        .phoneNumber(phoneNumber)
        .email(email)
        .status(status)
        .build();
  }
}
