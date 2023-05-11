package com.align.persistence.entity;

import com.align.dto.UserDto;
import com.align.dto.UserProfileDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "user")
@Table
public class UserEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private String password;

  @Column
  private String email;

  public UserEntity() {
  }

  public UserEntity(Long id, String name, String password, String email) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserEntity that = (UserEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, password, email);
  }

  public static UserProfileDto toProfileDto(UserEntity user) {
    UserProfileDto dto = new UserProfileDto();
    if (user != null) {
      dto.setId(user.getId());
      dto.setName(user.getName());
      dto.setEmail(user.getEmail());
    }
    return dto;
  }

  public static UserDto toDto(UserEntity userEntity) {
    UserDto dto = new UserDto();
    if (userEntity != null) {
      dto.setName(userEntity.getName());
      dto.setEmail(userEntity.getEmail());
    }
    return dto;
  }

  public static UserEntity fromDto(UserDto userDto) {
    UserEntity userEntity = new UserEntity();
    userEntity.setName(userDto.getName());
    userEntity.setEmail(userDto.getEmail());
    userEntity.setPassword(userDto.getPassword());
    return userEntity;
  }
}
