package com.align.service;

import com.align.persistence.entity.PostsEntity;
import com.align.persistence.repository.PostsRepository;
import com.align.web.dto.PostsDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostsService {
    private final PostsRepository postsRepository;

    private final UserFollowerService userFollowerService;

    public PostsService(PostsRepository postsRepository, UserFollowerService userFollowerService) {
        this.postsRepository = postsRepository;
        this.userFollowerService = userFollowerService;
    }

    /**
     * Persist post information to db.
     *
     * @param posts post details
     */
    public void post(PostsDto posts) {
        postsRepository.save(PostsEntity.fromDto(posts));
    }

    /**
     *
     * Get all posts message for users who followed login user or be followed by login user.
     *
     * @param username
     * @return
     */
    public List<PostsDto> getPosts(String username) {
        // Set can remove duplicated data
        Set<String> allUsers = new HashSet<>();
        allUsers.addAll(userFollowerService.getFollowedUsers(username).stream().map(u -> u.getUsername()).collect(Collectors.toSet()));
        allUsers.addAll(userFollowerService.getFollowingUsers(username).stream().map(u -> u.getUsername()).collect(Collectors.toSet()));
        List<PostsDto> posts = new ArrayList<>();
        for(String user : allUsers) {
            posts.addAll(postsRepository.findByPosterName(user).stream().map(p -> PostsEntity.toDto(p))
                    .collect(Collectors.toList()));
        }
        return posts;
    }
}
