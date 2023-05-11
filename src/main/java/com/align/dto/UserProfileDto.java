package com.align.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserProfileDto implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String name;
  private String email;

  private String token;

  public UserProfileDto(Long id, String name, String email, String token) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public UserProfileDto() {}

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserProfileDto userDto = (UserProfileDto) o;
    return Objects.equals(id, userDto.id) && Objects.equals(name, userDto.name) && Objects.equals(email, userDto.email) && Objects.equals(token, userDto.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, token);
  }
}
