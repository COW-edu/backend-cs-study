package com.example.koseonje.member.domain;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.example.koseonje.group.domain.Group;
import jakarta.persistence.Column;
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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  private String name;

  private String phoneNumber;

  private String email;

  @Enumerated(EnumType.STRING)
  private InvitationStatus status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "group_id")
  private Group group;

  @Builder
  private Member(String name, String phoneNumber, String email, InvitationStatus status) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.status = status;
  }

  public void joinGroup(final Group group) {
    this.group = group;
    group.addMember(this);
  }

  public void activateInvitationStatus() {
    this.status = InvitationStatus.ACTIVATED;
  }
}
