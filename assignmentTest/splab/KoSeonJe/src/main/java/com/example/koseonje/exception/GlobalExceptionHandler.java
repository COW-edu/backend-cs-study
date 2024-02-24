//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.koseonje.exception;

import com.example.koseonje.exception.response.ResponseMessage;
import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({IllegalArgumentException.class})
  public ResponseMessage illegalExceptionHandler(IllegalArgumentException e) {
    return new ResponseMessage("잘못된 요청", e.getMessage());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({MessagingException.class})
  public ResponseMessage messageExceptionHandler(MessagingException e) {
    return new ResponseMessage("메시지 에러", e.getMessage());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({UnsupportedEncodingException.class})
  public ResponseMessage encodingExceptionHandler(UnsupportedEncodingException e) {
    return new ResponseMessage("인코딩 에러", e.getMessage());
  }
}
