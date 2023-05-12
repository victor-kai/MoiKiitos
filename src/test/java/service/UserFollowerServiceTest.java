package service;

import com.align.persistence.repository.UserFollowerRepository;
import com.align.persistence.repository.UserRepository;
import com.align.service.UserFollowerService;
import com.align.web.dto.UserFollowerDto;
import com.align.web.exceptions.CommonException;
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

public class UserFollowerServiceTest {
  @InjectMocks
  private UserFollowerService userFollowerService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserFollowerRepository userFollowerRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getUserTest() {
    when(userRepository.findByNameOrEmail(anyString())).thenReturn(TestUtil.mockUserEntity());

    UserFollowerDto userDto = userFollowerService.getUser("admin");

    Assertions.assertNotNull(userDto);
  }

  @Test
  public void followWithFollowerNotExistsTest() {
    when(userRepository.findByName(anyString())).thenReturn(null);
    when(userFollowerRepository.save(any())).thenReturn(null);

    Assertions.assertThrows(CommonException.class, () -> userFollowerService.follow("admin", "user"));
  }

  @Test
  public void followTest() {
    when(userRepository.findByName(anyString())).thenReturn(TestUtil.mockUserEntity());
    when(userFollowerRepository.save(any())).thenReturn(null);

    userFollowerService.follow("admin", "user");
  }

  @Test
  public void unfollowTest() {
    doNothing().when(userFollowerRepository).deleteByUsernameAndFollower(anyString(), anyString());

    userFollowerService.unfollow("user", "follower");
  }

  @Test
  public void userFollowerExistsTest() {
    when(userFollowerRepository.findByUsernameAndFollowerName(anyString(), anyString())).thenReturn(null);

    boolean exists = userFollowerService.userFollowerExists("user", "follower");

    Assertions.assertTrue(!exists);
  }

  @Test
  public void getFollowedUsersTest() {
    when(userFollowerRepository.findByFollowerName(anyString())).thenReturn(TestUtil.mockUserFollowerEntities());

    List<UserFollowerDto> users = userFollowerService.getFollowedUsers("user");

    Assertions.assertNotNull(users);
    Assertions.assertTrue(users.size() > 0);
  }

  @Test
  public void getFollowingUsersTest() {
    when(userFollowerRepository.findByUsername(anyString())).thenReturn(TestUtil.mockUserFollowerEntities());

    List<UserFollowerDto> users = userFollowerService.getFollowingUsers("user");

    Assertions.assertNotNull(users);
    Assertions.assertTrue(users.size() > 0);
  }
}
