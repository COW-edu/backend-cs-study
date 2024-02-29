package com.splab.invitation.domain.member.service.dto;

import com.splab.invitation.domain.member.entity.InvitationStatus;
import com.splab.invitation.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InviteMemberCommand {

  @Schema(example = "고건")
  @NotBlank(message = "이름은 필수 항목입니다.")
  private String name;
  @Schema(example = "010-3372-4052")
  @NotBlank(message = "전화번호는 필수 항목입니다.")
  private String phone;
  @Schema(example = "rhrjs0131@naver.com")
  @NotBlank(message = "이메일은 필수 항목입니다.")
  private String email;
  @Schema(example = "안녕하세요? 귀하를 SPLAB 그룹에 초대합니다.")
  @Size(max = 100, message = "0 ~ 100 글자 사이에서 입력할 수 있습니다.")
  private String content;

  public Member toEntity() {
    return Member.builder()
        .name(name)
        .phone(phone)
        .email(email)
        .status(InvitationStatus.PENDING)
        .build();
  }
}
