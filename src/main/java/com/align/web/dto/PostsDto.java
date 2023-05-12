package com.align.web.dto;

import java.io.Serializable;
import java.util.Objects;

public class PostsDto implements Serializable {
    private Long posterId;
    private String posterName;
    private String message;

    public PostsDto(){}

    public PostsDto(Long posterId, String posterName, String message) {
        this.posterId = posterId;
        this.posterName = posterName;
        this.message = message;
    }

    public Long getPosterId() {
        return posterId;
    }

    public void setPosterId(Long posterId) {
        this.posterId = posterId;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostsDto postsDto = (PostsDto) o;
        return Objects.equals(posterId, postsDto.posterId) && Objects.equals(posterName, postsDto.posterName) && Objects.equals(message, postsDto.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posterId, posterName, message);
    }
}
