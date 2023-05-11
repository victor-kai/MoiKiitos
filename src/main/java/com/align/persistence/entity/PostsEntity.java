package com.align.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "posts")
public class PostsEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "poster_id")
  private Long posterId;

  @Column(name = "poster_name")
  private String posterName;

  @Column
  private String message;

  public PostsEntity() {}

  public PostsEntity(Long id, Long posterId, String posterName, String message) {
    this.id = id;
    this.posterId = posterId;
    this.posterName = posterName;
    this.message = message;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    PostsEntity that = (PostsEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(posterId, that.posterId) && Objects.equals(posterName, that.posterName) && Objects.equals(message, that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, posterId, posterName, message);
  }
}
