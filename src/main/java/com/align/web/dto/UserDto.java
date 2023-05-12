package com.align.web.dto;

import java.util.Objects;

public class UserDto {
  private String name;
  private String email;
  private String password;

  public UserDto() {}

  public UserDto(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserDto userDto = (UserDto) o;
    return Objects.equals(name, userDto.name) && Objects.equals(email, userDto.email) && Objects.equals(password, userDto.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email, password);
  }
}
