package com.example.koseonje.link.domain;

import com.example.koseonje.group.domain.Group;
import com.example.koseonje.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LINK")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Link {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private UUID accessKey;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "group_id")
  private Group group;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @Enumerated(EnumType.STRING)
  private EnableStatus enableStatus;

  @Builder
  public Link(Group group, Member member) {
    this.group = group;
    this.member = member;
    this.enableStatus = EnableStatus.ENABLED;
    this.accessKey = UUID.randomUUID();
  }

  public static Link create(Group group, Member member) {
    return Link.builder()
        .group(group)
        .member(member)
        .build();
  }

  public void expireStatus() {
    this.enableStatus = EnableStatus.EXPIRATION;
  }

}

