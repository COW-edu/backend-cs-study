package com.example.banking.common.api.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {
  OK("요청에 성공하였습니다."),
  BAD_REQUEST("잘못된 요청입니다."),
  INTERNAL_SEVER_ERROR("서버 에러"),
  ILLEGAL_VALUE("해당 입력 값에 맞는 정보는 없습니다.");

  private final String resultMessage;
}
