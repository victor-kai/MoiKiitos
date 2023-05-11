package com.align.persistence.repository;

import com.align.persistence.entity.UserFollowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFollowerRepository extends JpaRepository<UserFollowerEntity, Long> {

  UserFollowerEntity findByUsernameAndFollowerName(String username, String followerName);

  List<UserFollowerEntity> findByUsername(String username);

  List<UserFollowerEntity> findByFollowerName(String followerName);

  @Query(value = "delete from user_follower where user_name = :username and follower_name = :follower", nativeQuery = true)
  @Modifying
  void deleteByUsernameAndFollower(String username, String follower);
}
