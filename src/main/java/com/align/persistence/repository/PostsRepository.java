package com.align.persistence.repository;

import com.align.persistence.entity.PostsEntity;
import com.align.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<PostsEntity, Long> {
  List<PostsEntity> findByPosterId(Long posterId);
  List<PostsEntity> findByPosterName(String username);
}
