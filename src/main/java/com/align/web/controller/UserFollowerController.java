package com.align.web.controller;

import com.align.service.UserFollowerService;
import com.align.service.UserService;
import com.align.web.response.RestApiResponse;
import com.align.web.dto.UserFollowerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/moki")
public class UserFollowerController {
  @Autowired
  private UserService userService;

  @Autowired
  private UserFollowerService userFollowerService;

  @GetMapping("/users")
  @ResponseStatus(HttpStatus.OK)
  public RestApiResponse<UserFollowerDto> users(@RequestParam(name = "usernameOrEmail") String searchNameOrEmail) {
    return new RestApiResponse<>(userFollowerService.getUser(searchNameOrEmail));
  }

  @PostMapping("/follow")
  @ResponseStatus(HttpStatus.OK)
  public RestApiResponse<Object> follow(@RequestParam(name = "watchUser") String username,
                                              @RequestParam(name = "loginUser") String follower) {
    if (userFollowerService.userFollowerExists(username, follower)) {
      return new RestApiResponse<>(String.format("you have followed %s already", username));
    }
    userFollowerService.follow(username, follower);
    return new RestApiResponse<>(String.format("followed %s %s", username, "successful"));
  }

  @DeleteMapping("/unfollow")
  @ResponseStatus(HttpStatus.OK)
  public RestApiResponse<Object> unfollow(@RequestParam(name = "watchUser") String username,
                                          @RequestParam(name = "loginUser") String follower) {
    userFollowerService.unfollow(username, follower);
    return new RestApiResponse<>("unfollow successfully");
  }

  @GetMapping("/followers")
  @ResponseStatus(HttpStatus.OK)
  public RestApiResponse<List<UserFollowerDto>> listFollowers(@RequestParam(name = "loginUser") String username) {
    return new RestApiResponse<>(userFollowerService.getFollowedUsers(username));
  }

  @GetMapping("/followings")
  @ResponseStatus(HttpStatus.OK)
  public RestApiResponse<List<UserFollowerDto>> listFollowings(@RequestParam(name = "loginUser") String username) {
    return new RestApiResponse<>(userFollowerService.getFollowingUsers(username));
  }
}
