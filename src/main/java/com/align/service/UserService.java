package com.align.service;

import com.align.dto.UserDto;
import com.align.persistence.entity.UserEntity;
import com.align.persistence.repository.UserRepository;
import com.align.utils.AlgorithmUtil;
import com.align.web.RestApiResponse;
import com.align.web.exceptions.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  private final Logger LOG = LoggerFactory.getLogger(UserService.class);

  /**
   * check user login credential
   *
   * @param usernameOrEmail username or email
   * @param password password from user, better to have base64 encode
   * @return user info, otherwise empty.
   */
  public UserEntity login(String usernameOrEmail, String password) {
    try {
      return userRepository.findByNameOrEmailAndPassword(usernameOrEmail, AlgorithmUtil.sha256(password));
    } catch (Exception e) {
      LOG.error("login exception", e);
      throw new CommonException("Failed to login");
    }
  }

  /**
   * persist user registry info.
   *
   * @param user user information
   * @return user
   */
  @Transactional
  public UserEntity register(UserDto user)  {
    if (checkIfUserExists(user.getName(), user.getEmail())) {
      throw new CommonException("username or email has been registered, try another one.");
    }
    try {
      user.setPassword(AlgorithmUtil.sha256(user.getPassword()));
      return userRepository.save(UserEntity.fromDto(user));
    } catch (Exception e) {
      LOG.error("register exception", e);
      throw new CommonException("Failed to register");
    }
  }

  /**
   * Check if username or email has been registered.
   *
   * @param username username need to check if already registered
   * @param email email need to check if already registered
   * @return true if no register
   */
  public boolean checkIfUserExists(String username, String email) {
    UserEntity userEntity = userRepository.findByName(username);
    if (userEntity != null) {
      return true;
    }
    userEntity = userRepository.findByEmail(email);
    return userEntity != null;
  }
}
