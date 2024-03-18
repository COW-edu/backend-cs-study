package com.splab.invitation.presentation.common;

public enum ResponseType {
  OK(200, "API 요청이 성공했습니다."),
  BAD_REQUEST(400, "API 요청이 성공했습니다."),
  INTERNAL_SERVER_ERROR(500, "에러가 발생했습니다.");

  private int code;
  private String message;


  ResponseType(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
