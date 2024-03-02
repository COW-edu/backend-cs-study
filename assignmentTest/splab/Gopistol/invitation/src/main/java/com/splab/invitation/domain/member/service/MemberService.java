package com.splab.invitation.domain.member.service;

import com.splab.invitation.domain.member.entity.Member;
import com.splab.invitation.domain.member.service.dto.InviteMemberCommand;
import com.splab.invitation.persistence.member.MemberRepository;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

  private final EmailService emailService;
  private final MemberRepository repository;
  private static final Integer KEY_MAX_LENGTH = 6;
  private static final Integer SLICE_INDEX = 0;
  private static final ConcurrentHashMap<String, String> db = new ConcurrentHashMap<>();

  public String create(InviteMemberCommand command) {
    Member member = command.toEntity();
    repository.save(member);

    String passKey = generatePassKey();
    String email = command.getEmail();

    db.put(email, passKey);

    emailService.sendEmail(command, passKey);

    return email;
  }

  public void join(String email, String key) {
    String passKey = db.get(email);

    if (passKey.equals(key)) {

      Member member = repository.findByEmail(email)
          .orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 사용자가 존재하지 않습니다."));

      member.updateStatus();
      db.remove(email); // 기존 저장 정보 삭제
      return;
    }

    throw new IllegalArgumentException("인증 키가 올바르지 않습니다.");
  }

  private String generatePassKey() {
    // 나노초를 앞에서부터 6번째 자리까지 자르기
    long currentMilliSecond = Instant.now().toEpochMilli();
    String result = String.valueOf(currentMilliSecond);

    return result.substring(SLICE_INDEX, KEY_MAX_LENGTH);
  }
}
