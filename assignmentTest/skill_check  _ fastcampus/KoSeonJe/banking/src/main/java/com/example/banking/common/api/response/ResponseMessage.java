package com.example.banking.common.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ResponseMessage {

  private ResponseStatus responseStatus;
  private String resultMessage;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Object data;

  public static ResponseMessage of(ResponseStatus responseStatus, Object data) {
    return ResponseMessage.builder()
        .responseStatus(responseStatus)
        .resultMessage(responseStatus.getResultMessage())
        .data(data)
        .build();
  }

  public static ResponseMessage of(ResponseStatus responseStatus) {
    return ResponseMessage.builder()
        .responseStatus(responseStatus)
        .resultMessage(responseStatus.getResultMessage())
        .build();
  }
}