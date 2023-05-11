package com.align.web.controller;

import com.align.service.UserFollowerService;
import com.align.service.UserService;
import com.align.web.RestApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/moki")
public class UserFollowerController {
  @Autowired
  private UserService userService;

  @Autowired
  private UserFollowerService userFollowerService;

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
}
