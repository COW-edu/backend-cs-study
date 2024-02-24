package com.example.koseonje.member.controller;

import com.example.koseonje.member.controller.dto.request.InviteMemberRequest;
import com.example.koseonje.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/members"})
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

  @PostMapping({"/invitation"})
  public ResponseEntity<Object> invite(@RequestBody InviteMemberRequest inviteMemberRequest) {
    memberService.invite(inviteMemberRequest.toServiceDto());
    return ResponseEntity.ok().build();
  }

  @PostMapping({"/join"})
  public ResponseEntity<Object> join(@RequestParam String accessKey) {
    memberService.join(accessKey);
    return ResponseEntity.ok().build();
  }
}

