package service;

import com.align.dto.UserProfileDto;
import com.align.persistence.entity.UserEntity;
import com.align.persistence.repository.UserRepository;
import com.align.service.AuthService;
import com.align.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.TestUtil;

import java.security.NoSuchAlgorithmException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthServiceTest {
  @InjectMocks
  private AuthService authService;
  @Mock
  private UserService userService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void loginTest() throws NoSuchAlgorithmException {
    UserEntity userEntity = TestUtil.mockUserEntity();

    when(userService.login(anyString(), anyString())).thenReturn(userEntity);

    UserProfileDto userDto = authService.loginAndAuthorize("admin", "123");

    verify(userService, times(1)).login(anyString(), anyString());
    Assertions.assertNotNull(userDto);
  }
}
