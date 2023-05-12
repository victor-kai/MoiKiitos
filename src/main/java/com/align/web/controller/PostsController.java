package com.align.web.controller;

import com.align.constant.Constants;
import com.align.service.PostsService;
import com.align.web.response.RestApiResponse;
import com.align.web.dto.PostsDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/moki")
public class PostsController {

    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @PostMapping("/post")
    public RestApiResponse<Object> posts(@RequestBody @Valid PostsDto posts) {
        postsService.post(posts);
        return new RestApiResponse<>(null, Constants.POST_SUCCEED);
    }

    @GetMapping("/feedlist")
    public RestApiResponse<List<PostsDto>> listPosts(@RequestParam("username") String username) {
        return new RestApiResponse<>(postsService.getPosts(username));
    }
}
