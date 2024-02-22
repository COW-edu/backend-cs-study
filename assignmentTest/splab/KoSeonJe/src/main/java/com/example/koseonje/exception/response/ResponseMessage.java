package com.example.koseonje.exception.response;


public class ResponseMessage {
  private String customMessage;
  private String exceptionMessage;

  public ResponseMessage(String customMessage, String exceptionMessage) {
    this.customMessage = customMessage;
    this.exceptionMessage = exceptionMessage;
  }
}
