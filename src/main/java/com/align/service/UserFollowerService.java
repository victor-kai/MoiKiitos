package com.align.service;

import com.align.persistence.entity.UserEntity;
import com.align.persistence.entity.UserFollowerEntity;
import com.align.persistence.repository.UserFollowerRepository;
import com.align.persistence.repository.UserRepository;
import com.align.web.dto.UserFollowerDto;
import com.align.web.exceptions.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFollowerService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserFollowerRepository userFollowerRepository;

  private static final Logger LOG = LoggerFactory.getLogger(UserFollowerService.class);

  /**
   * Search user by name or email
   *
   * @param searchNameOrEmail user name or email used to search for
   * @return user name and email
   */
  public UserFollowerDto getUser(String searchNameOrEmail) {
    UserEntity user = userRepository.findByNameOrEmail(searchNameOrEmail);
    UserFollowerDto dto = new UserFollowerDto();
    dto.setUsername(user.getName());
    dto.setUserEmail(user.getEmail());
    dto.setUserId(user.getId());
    return dto;
  }

  /**
   * follower other user
   *
   * @param username other user login user want to follow
   * @param follower login user
   */
  @Transactional
  public void follow(String username, String follower) {
    UserEntity user = userRepository.findByName(username);
    UserEntity uf = userRepository.findByName(follower);
    if (user == null || uf == null) {
      throw new CommonException("follower username or login username is invalid");
    }
    UserFollowerEntity userFollowerEntity = new UserFollowerEntity();
    userFollowerEntity.setUsername(user.getName());
    userFollowerEntity.setUserEmail(user.getEmail());
    userFollowerEntity.setFollowerName(uf.getName());
    userFollowerEntity.setFollowerEmail(uf.getEmail());
    userFollowerRepository.save(userFollowerEntity);
  }

  /**
   * unfollow user
   *
   * @param username user that login user followed
   * @param follower login user
   */
  @Transactional
  public void unfollow(String username, String follower) {
    userFollowerRepository.deleteByUsernameAndFollower(username, follower);
  }

  public boolean userFollowerExists(String username, String follower) {
    return userFollowerRepository.findByUsernameAndFollowerName(username, follower) != null;
  }

  /**
   * Get all users that given username followed.
   * So, generally, this is for finding who I was followed from web page.
   *
   * @param username user name
   * @return list of following users' name and email
   */
  public List<UserFollowerDto> getFollowedUsers(String username) {
    return userFollowerRepository.findByFollowerName(username).stream()
            .map(uf -> UserFollowerEntity.toFollowedUserDto(uf)).collect(Collectors.toList());
  }

  /**
   * Get all users that followed given username.
   * So, this is for finding who followed me from web page.
   *
   * @param username login username
   * @return list of followers' name and email
   */
  public List<UserFollowerDto> getFollowingUsers(String username) {
    return userFollowerRepository.findByUsername(username).stream()
            .map(uf -> UserFollowerEntity.toUserFollowingUserDto(uf)).collect(Collectors.toList());
  }
}
