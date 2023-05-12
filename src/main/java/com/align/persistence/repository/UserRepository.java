package com.align.persistence.repository;

import com.align.persistence.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  @Query(value = "select * from user where name = :usernameOrEmail or email = :usernameOrEmail and password = :password", nativeQuery = true)
  UserEntity findByNameOrEmailAndPassword(String usernameOrEmail, String password);

  @Query(value = "select * from user where name like concat('%',:usernameOrEmail,'%') or email = :usernameOrEmail", nativeQuery = true)
  UserEntity findByNameOrEmail(String usernameOrEmail);

  UserEntity findByName(String username);

  UserEntity findByEmail(String email);
}
