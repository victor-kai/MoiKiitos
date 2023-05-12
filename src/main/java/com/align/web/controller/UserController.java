package com.align.web.controller;

import com.align.constant.Constants;
import com.align.persistence.entity.UserEntity;
import com.align.service.AuthService;
import com.align.service.UserService;
import com.align.web.dto.UserDto;
import com.align.web.dto.UserProfileDto;
import com.align.web.response.RestApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
  private final UserService userService;

  private final AuthService authService;

  public UserController(UserService userService, AuthService authService) {
    this.userService = userService;
    this.authService = authService;
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public RestApiResponse<UserProfileDto> login(@RequestParam(name = "usernameOrEmail") String usernameOrEmail,
                                              @RequestParam(name = "password") String password) {
    UserProfileDto profileDto = authService.loginAndAuthorize(usernameOrEmail, password);
    return new RestApiResponse<>(profileDto, Constants.OK);
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.OK)
  public RestApiResponse<UserDto> register(@RequestBody @Valid UserDto user) {
    UserEntity userEntity = userService.register(user);
    return new RestApiResponse<>(UserEntity.toDto(userEntity), Constants.REGISTRATION_SUCCEED);
  }
}
