package com.align.persistence.entity;

import com.align.web.dto.UserFollowerDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "user_follower")
@Table
public class UserFollowerEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_name")
  private String username;

  @Column(name = "user_email")
  private String userEmail;

  @Column(name = "follower_name")
  private  String followerName;

  @Column(name = "follower_email")
  private  String followerEmail;

  public UserFollowerEntity() {}

  public UserFollowerEntity(Long id, String username, String userEmail, String followerName, String followerEmail) {
    this.id = id;
    this.username = username;
    this.userEmail = userEmail;
    this.followerName = followerName;
    this.followerEmail = followerEmail;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getFollowerName() {
    return followerName;
  }

  public void setFollowerName(String followerName) {
    this.followerName = followerName;
  }

  public String getFollowerEmail() {
    return followerEmail;
  }

  public void setFollowerEmail(String followerEmail) {
    this.followerEmail = followerEmail;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserFollowerEntity that = (UserFollowerEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(userEmail, that.userEmail) && Objects.equals(followerName, that.followerName) && Objects.equals(followerEmail, that.followerEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, userEmail, followerName, followerEmail);
  }

  public static UserFollowerDto toUserFollowingUserDto(UserFollowerEntity entity) {
    UserFollowerDto dto = new UserFollowerDto();
    if (entity != null) {
      dto.setUsername(entity.getFollowerName());
      dto.setUserEmail(entity.getFollowerEmail());
    }
    return dto;
  }

  public static UserFollowerDto toFollowedUserDto(UserFollowerEntity entity) {
    UserFollowerDto dto = new UserFollowerDto();
    if (entity != null) {
      dto.setUsername(entity.getUsername());
      dto.setUserEmail(entity.getUserEmail());
    }
    return dto;
  }
}
