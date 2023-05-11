package com.align.web.exceptions;

public class CommonException extends RuntimeException{
  private String response;

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public CommonException(String message) {
    super(message);
  }

  public CommonException(String message, String response) {
    super(message);
    this.response = response;
  }
}
