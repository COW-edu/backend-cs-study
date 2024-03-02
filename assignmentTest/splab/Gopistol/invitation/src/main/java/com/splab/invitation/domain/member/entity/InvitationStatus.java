package com.splab.invitation.domain.member.entity;

public enum InvitationStatus {
  ACTIVATED("활성화"),
  PENDING("초대 발송 후 참여 대기");

  private String status;
  InvitationStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
