package com.splab.invitation.presentation.member;

import com.splab.invitation.domain.member.service.EmailService;
import com.splab.invitation.domain.member.service.MemberService;
import com.splab.invitation.domain.member.service.dto.InviteMemberCommand;
import com.splab.invitation.presentation.common.ApiResponse;
import jakarta.validation.Valid;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
  private final EmailService emailService;

  @PostMapping(value = "/invite")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<String> invite(@Valid @RequestBody InviteMemberCommand command) {
    String email = memberService.create(command);

    return ApiResponse.success(email);
  }

  @GetMapping(value = "/joinMember")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<Void> join(
      @RequestParam(name = "email") String email,
      @RequestParam(name = "passKey") String key) {
    memberService.join(email, key);

    return ApiResponse.success();
  }
}
