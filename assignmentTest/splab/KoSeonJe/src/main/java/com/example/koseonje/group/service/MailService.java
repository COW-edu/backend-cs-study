package com.example.koseonje.group.service;

import com.example.koseonje.group.domain.Group;
import com.example.koseonje.link.domain.Link;
import com.example.koseonje.link.service.LinkService;
import com.example.koseonje.member.domain.Member;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
  private final JavaMailSender javaMailSender;
  private final GroupService groupService;
  private final LinkService linkService;
  private static final String INVITE_LINK = "http://localhost:8080/api/members/join?accessKey=";
  private static final String MAIL_SUBJECT = "그룹 초대 링크";
  public static final String CHARSET = "utf-8";
  public static final String ADDRESS_EMAIL = "a01039261344@gmail.com";
  public static final String ADDRESS_NAME = "고선제";

  public void invite(String groupName, Member member) {
    try {
      Group group = groupService.findByName(groupName);
      Link link = Link.create(group, member);
      linkService.save(link);
      MimeMessage mail = this.createMailContent(link.getAccessKey(), member.getEmail());
      javaMailSender.send(mail);
    } catch (MessagingException e) {
      log.error("메일 전송 실패");
    } catch (UnsupportedEncodingException e) {
      log.error("인코딩 실패");
    }
  }

  private MimeMessage createMailContent(UUID key, String email) throws MessagingException, UnsupportedEncodingException {
    MimeMessage message = this.javaMailSender.createMimeMessage();
    message.addRecipients(RecipientType.TO, email);
    message.setSubject(MAIL_SUBJECT);
    message.setText(INVITE_LINK + key, CHARSET);
    message.setFrom(new InternetAddress(ADDRESS_EMAIL, ADDRESS_NAME));
    return message;
  }
}

