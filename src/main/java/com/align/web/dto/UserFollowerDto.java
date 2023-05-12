package com.align.web.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserFollowerDto implements Serializable {

    private Long userId;
    private String username;
    private String userEmail;

    public UserFollowerDto(){}

    public UserFollowerDto(String username, String userEmail, Long userId) {
        this.username = username;
        this.userEmail = userEmail;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFollowerDto that = (UserFollowerDto) o;
        return Objects.equals(userId, that.userId) && Objects.equals(username, that.username) && Objects.equals(userEmail, that.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, userEmail);
    }
}
