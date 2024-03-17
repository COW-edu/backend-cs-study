package com.splab.invitation.presentation.member;

import com.splab.invitation.domain.member.service.EmailInvitationService;
import com.splab.invitation.domain.member.service.MemberService;
import com.splab.invitation.domain.member.service.dto.InviteMemberCommand;
import com.splab.invitation.presentation.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MemberController implements MemberApiPresentation {

  private final MemberService memberService;
  private final EmailInvitationService emailInvitationService;

  @PostMapping(value = "/invitation:send")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<Long> invite(@Valid @RequestBody InviteMemberCommand command) {
    Long id = memberService.create(command);
    emailInvitationService.sendEmail(command.getEmail(), command.getContent());

    return ApiResponse.success(id);
  }

  @GetMapping(value = "/invitation:join")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<Void> join(
      @RequestParam(name = "email") String email,
      @RequestParam(name = "invitationCode") String code) {
    memberService.join(email, code);

    return ApiResponse.success();
  }
}
