package controller;

import com.align.constant.Constants;
import com.align.service.PostsService;
import com.align.web.controller.PostsController;
import com.align.web.controller.UserController;
import com.align.web.dto.PostsDto;
import com.align.web.dto.UserDto;
import com.align.web.dto.UserProfileDto;
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

public class PostsControllerTest {
  @InjectMocks
  private PostsController postsController;
  @Mock
  private PostsService postsService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void postTest() {
    doNothing().when(postsService).post(any());

    RestApiResponse<Object> response = postsController.posts(TestUtil.mockPostDto());

    Assertions.assertNull(response.getError());
    Assertions.assertEquals(Constants.POST_SUCCEED, response.getMessage());
  }

  @Test
  public void feedListTest() {
    when(postsService.getPosts(anyString())).thenReturn(Lists.newArrayList(TestUtil.mockPostDto()));

    RestApiResponse<List<PostsDto>> response = postsController.listPosts("user");

    Assertions.assertTrue(response.getDataObject().size() > 0);
  }
}
