package service;

import com.align.persistence.repository.PostsRepository;
import com.align.service.PostsService;
import com.align.service.UserFollowerService;
import com.align.web.dto.PostsDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PostsServiceTest {
  @InjectMocks
  private PostsService postsService;
  @Mock
  private PostsRepository postsRepository;
  @Mock
  private UserFollowerService userFollowerService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void postTest() {
    PostsDto postsDto = TestUtil.mockPostDto();

    when(postsRepository.save(any())).thenReturn(null);

    postsService.post(postsDto);

    verify(postsRepository, times(1)).save(any());
  }

  @Test
  public void getPostsTest() {
    when(userFollowerService.getFollowedUsers(anyString())).thenReturn(new ArrayList<>());

    when(userFollowerService.getFollowingUsers(anyString())).thenReturn(TestUtil.mockUserFollowers());
    when(postsRepository.findByPosterName(anyString())).thenReturn(TestUtil.mockPostEntity());

    List<PostsDto> posts = postsService.getPosts("mack");

    verify(postsRepository, times(1)).findByPosterName(anyString());
    Assertions.assertNotNull(posts);
  }
}
