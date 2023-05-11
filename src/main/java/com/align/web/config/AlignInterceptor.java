package com.align.web.config;

import com.align.Constants;
import com.align.web.RestApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AlignInterceptor extends HandlerInterceptorAdapter {
  private static final Logger LOG = LoggerFactory.getLogger(AlignInterceptor.class);
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    LOG.info("--------------- Authorization Verification ---------------");
    // for poc authentication requirement, check token here to replace auth service such as Auth0 and etc.
    if (!new AntPathMatcher().match("/moki/**", request.getRequestURI())) {
      return true;
    }
    String token = request.getHeader(Constants.AUTHORIZATION);
    if (StringUtils.isEmpty(token)) {
      return responseError(response, new RestApiResponse<>(Constants.AUTHORIZATION_REQUIRED), HttpStatus.UNAUTHORIZED.value());
    }
    if (!Constants.mockedToken.equals(token)) {
      return responseError(response, new RestApiResponse<>(Constants.AUTHORIZATION_INVALID), HttpStatus.FORBIDDEN.value());
    }
    return true;
  }

  private boolean responseError(HttpServletResponse response, RestApiResponse<Object> apiResponse, int httpStatus){
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    } catch (IOException e) {
      e.printStackTrace();
    }
    response.setStatus(httpStatus);
    return false;
  }
}
