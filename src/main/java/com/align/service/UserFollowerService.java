package com.align.service;

import com.align.persistence.entity.UserEntity;
import com.align.persistence.entity.UserFollowerEntity;
import com.align.persistence.repository.UserFollowerRepository;
import com.align.persistence.repository.UserRepository;
import com.align.web.exceptions.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserFollowerService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserFollowerRepository userFollowerRepository;

  private static final Logger LOG = LoggerFactory.getLogger(UserFollowerService.class);

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
}
