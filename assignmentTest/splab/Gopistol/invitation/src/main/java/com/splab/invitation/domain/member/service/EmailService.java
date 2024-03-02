package com.splab.invitation.domain.member.service;

import com.splab.invitation.domain.member.entity.Member;
import com.splab.invitation.domain.member.service.dto.InviteMemberCommand;
import com.splab.invitation.persistence.member.MemberRepository;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {

  private final MemberRepository repository;
  private final JavaMailSender mailSender;
  private final ResourceLoader resourceLoader;
  private static final String prefix = "http://localhost:8080/members/joinMember?";
  private static final String CONTENT_PLACEHOLDER = "CONTENT";
  private static final String LINK_PLACEHOLDER = "YOUR_INVITATION_LINK";

  public void sendEmail(InviteMemberCommand command, String passKey) {
    // 링크 생성
    String link = prefix + "email=" + command.getEmail() + "&passKey=" + passKey;

    // 이메일 전송
    try {
      MimeMessage mail = createMailContent(command, link);
      mailSender.send(mail);

    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("메일 전송 실패");
    }

  }

  private MimeMessage createMailContent(InviteMemberCommand command, String link)
      throws Exception {
    String to = command.getEmail();
    String content = command.getContent();

    MimeMessage message = mailSender.createMimeMessage();

    message.addRecipients(RecipientType.TO, to);
    message.setSubject("링크 인증");

    Resource resource = resourceLoader.getResource("classpath:static/invitation-content.txt");
    String mailContent = Files.readString(Path.of(resource.getURI()));

    String emailText = mailContent.replace(LINK_PLACEHOLDER, link)
        .replace(CONTENT_PLACEHOLDER, content);

    message.setText(emailText, "utf-8", "html");
    message.setFrom(new InternetAddress("gopremium0131@gmail.com", "SPLAB"));

    return message;
  }
}
