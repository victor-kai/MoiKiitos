package com.align.web.controller;

import com.align.Constants;
import com.align.dto.UserDto;
import com.align.dto.UserProfileDto;
import com.align.persistence.entity.UserEntity;
import com.align.service.AuthService;
import com.align.service.UserService;
import com.align.web.RestApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private AuthService authService;

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public RestApiResponse<UserProfileDto> login(@RequestParam(name = "usernameOrEmail") String usernameOrEmail,
                                              @RequestParam(name = "password") String password) {
    UserProfileDto profileDto = authService.loginAndAuthorize(usernameOrEmail, password);
    return new RestApiResponse<>(profileDto, Constants.OK);
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.OK)
  public RestApiResponse<UserDto> register(@RequestBody UserDto user) {
    UserEntity userEntity = userService.register(user);
    return new RestApiResponse<>(UserEntity.toDto(userEntity), Constants.REGISTRATION_SUCCEED);
  }
}
