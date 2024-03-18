package com.splab.invitation.domain.member.service;

import jakarta.mail.Message.RecipientType;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Transactional
@RequiredArgsConstructor
@PropertySource("classpath:email.properties")
public class EmailInvitationService {

  private static final Integer KEY_MAX_LENGTH = 6;
  private static final Integer SLICE_INDEX = 0;
  private static final Map<String, String> db = new ConcurrentHashMap<>();

  @Value("${contents.placeholder}")
  private String CONTENT_PLACEHOLDER;
  @Value("${link.placeholder}")
  private String LINK_PLACEHOLDER;

  private final JavaMailSender mailSender;
  private final ResourceLoader resourceLoader;

  public void sendEmail(String email, String content) {
    // 초대 코드 생성
    String invitationCode = generateInvitationCode();
    db.put(email, invitationCode);

    // 링크 생성
    UriComponents link = UriComponentsBuilder.newInstance()
        .scheme("http")
        .host("localhost:8080")
        .path("/members/invitation:join")
        .queryParam("email", email)
        .queryParam("invitationCode", invitationCode)
        .build();

    System.out.println(link);

    // 이메일 전송
    try {

      MimeMessage mail = createMailContent(email, content, link.toUriString());

      mailSender.send(mail);

    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("메일 전송 실패");
    }

  }

  public boolean verifyMember(String email, String invitationCode) {

    return invitationCode.equalsIgnoreCase(db.get(email));
  }

  private MimeMessage createMailContent(String to, String content, String link)
      throws Exception {

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

  private String generateInvitationCode() {
    // 나노초를 앞에서부터 6번째 자리까지 자르기
    long currentMilliSecond = Instant.now().getNano();
    String result = String.valueOf(currentMilliSecond);

    return result.substring(SLICE_INDEX, KEY_MAX_LENGTH);
  }
}
