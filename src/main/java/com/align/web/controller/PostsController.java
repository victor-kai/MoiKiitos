package com.align.web.controller;

import com.align.service.PostsService;
import com.align.web.RestApiResponse;
import com.align.web.dto.PostsDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/moki")
public class PostsController {

    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @PostMapping("/post")
    public RestApiResponse<Object> posts(@RequestBody PostsDto posts) {
        postsService.post(posts);
        return new RestApiResponse<>(null, "Message post successful");
    }

    @GetMapping("/feedlist")
    public RestApiResponse<List<PostsDto>> listPosts(@RequestParam("username") String username) {
        return new RestApiResponse<>(postsService.getPosts(username));
    }
}
