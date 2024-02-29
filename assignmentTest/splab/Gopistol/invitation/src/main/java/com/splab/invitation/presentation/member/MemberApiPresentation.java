package com.splab.invitation.presentation.member;

import com.splab.invitation.domain.member.service.dto.InviteMemberCommand;
import com.splab.invitation.presentation.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.security.NoSuchAlgorithmException;

@Tag(name = "Member API")
public interface MemberApiPresentation {

  @Operation(summary = "멤버 초대")
  public ApiResponse<String> invite(InviteMemberCommand command) throws NoSuchAlgorithmException;

  @Operation(summary = "초대 수락")
  public ApiResponse<Void> join(String email, String key);
}
