package controller;

import com.align.constant.Constants;
import com.align.service.PostsService;
import com.align.service.UserFollowerService;
import com.align.service.UserService;
import com.align.web.controller.PostsController;
import com.align.web.controller.UserFollowerController;
import com.align.web.dto.PostsDto;
import com.align.web.dto.UserFollowerDto;
import com.align.web.response.RestApiResponse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.TestUtil;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserFollowerControllerTest {
  @InjectMocks
  private UserFollowerController userFollowerController;
  @Mock
  private UserService userService;
  @Mock
  private UserFollowerService userFollowerService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void usersTest() {
    when(userFollowerService.getUser(anyString())).thenReturn(TestUtil.mockUserFollowers().get(0));

    RestApiResponse<UserFollowerDto> response = userFollowerController.users("user");

    Assertions.assertNull(response.getError());
    Assertions.assertNotNull(response.getDataObject());
  }

  @Test
  public void followerExistsTest() {
    doNothing().when(userFollowerService).follow(anyString(), anyString());
    when(userFollowerService.userFollowerExists(anyString(), anyString())).thenReturn(true);

    RestApiResponse<Object> response = userFollowerController.follow("user", "follower");

    Assertions.assertNotNull(response.getError());
  }

  @Test
  public void followTest() {
    doNothing().when(userFollowerService).follow(anyString(), anyString());
    when(userFollowerService.userFollowerExists(anyString(), anyString())).thenReturn(false);

    RestApiResponse<Object> response = userFollowerController.follow("user", "follower");

    Assertions.assertNull(response.getError());
    Assertions.assertNotNull(response.getMessage());
  }

  @Test
  public void unfollowTest() {
    doNothing().when(userFollowerService).unfollow(anyString(), anyString());

    RestApiResponse<Object> response = userFollowerController.unfollow("user", "follower");

    Assertions.assertNull(response.getError());
    Assertions.assertNotNull(response.getMessage());
  }
}
