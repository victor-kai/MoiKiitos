package com.align.web.exceptions.handler;

import com.align.web.response.RestApiResponse;
import com.align.web.exceptions.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonHandler {
  private static final Logger LOG = LoggerFactory.getLogger(CommonHandler.class);

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public RestApiResponse<Object> globalExceptionHandle(Exception exception) {
    LOG.error("Handle exception: {}", exception.getMessage());
    return new RestApiResponse<>(exception.getMessage());
  }

  @ExceptionHandler(CommonException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public RestApiResponse<Object> commonExceptionHandle(Exception exception) {
    LOG.error("Handle common exception: {}", exception.getMessage());
    return new RestApiResponse<>(exception.getMessage());
  }
}
