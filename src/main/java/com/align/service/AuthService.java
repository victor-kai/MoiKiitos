package com.align.service;

import com.align.constant.Constants;
import com.align.persistence.entity.UserEntity;
import com.align.web.dto.UserProfileDto;
import com.align.web.exceptions.CommonException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final UserService userService;

  public AuthService(UserService userService) {
    this.userService = userService;
  }

  /**
   * Check username and password from register history, for every legal login return the authorized jwt token.
   * Token was a workaround for what from auth server,
   * so ideally, front will take along with this token in the following requests
   *
   * @param usernameOrEmail credential
   * @param password credential
   * @return user profile with token
   */
  public UserProfileDto loginAndAuthorize(String usernameOrEmail, String password) {
    UserEntity userEntity = userService.login(usernameOrEmail, password);
    if (userEntity == null){
      throw new CommonException("login credential is invalid");
    }
    UserProfileDto user = UserEntity.toProfileDto(userEntity);
    user.setToken(getToken(usernameOrEmail));
    return  user;
  }

  /**
   * Token should be like from some auth server like Auth0, but for POC here only return a constant instead.
   *
   * @param usernameOrEmail info tag to jwt token
   * @return jwt token
   */
  public String getToken(String usernameOrEmail) {
    return Constants.mockedToken;
  }
}
