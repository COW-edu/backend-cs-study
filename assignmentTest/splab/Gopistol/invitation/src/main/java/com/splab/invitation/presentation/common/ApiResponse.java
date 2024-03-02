package com.splab.invitation.presentation.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

  private Integer code;
  private String message;
  @JsonInclude(Include.NON_NULL)
  private T result;

  public static <T> ApiResponse<T> success() {
    return new ApiResponse<>(ResponseType.OK.getCode(), ResponseType.OK.getMessage(), null);
  }

  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(ResponseType.OK.getCode(), ResponseType.OK.getMessage(), data);
  }

  public static ApiResponse<Void> failure(ResponseType responseType,String message) {

    return new ApiResponse<>(responseType.getCode(), message, null);
  }
}
