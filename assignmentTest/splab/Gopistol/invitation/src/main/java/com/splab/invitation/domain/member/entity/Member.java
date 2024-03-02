package com.splab.invitation.domain.member.entity;

import com.splab.invitation.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String phone;

  @Column(nullable = false, unique = true)
  private String email;


  @Enumerated(EnumType.STRING)
  private InvitationStatus status;

  @Builder
  public Member(String name, String phone, String email, InvitationStatus status) {
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.status = status;
  }

  public void updateStatus() {
    this.status = InvitationStatus.ACTIVATED;
  }
}
