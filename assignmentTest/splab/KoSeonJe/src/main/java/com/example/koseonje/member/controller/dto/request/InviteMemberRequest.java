package com.example.koseonje.member.controller.dto.request;

import com.example.koseonje.member.service.dto.InviteServiceDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InviteMemberRequest {

  private String groupName;
  private String memberName;
  private String phoneNumber;
  private String email;

  public InviteServiceDto toServiceDto() {
    return InviteServiceDto.builder()
        .groupName(groupName)
        .memberName(memberName)
        .phoneNumber(phoneNumber)
        .email(email)
        .build();
  }
}
