package controller;

import com.align.constant.Constants;
import com.align.persistence.entity.UserEntity;
import com.align.service.AuthService;
import com.align.service.UserService;
import com.align.web.controller.UserController;
import com.align.web.dto.UserDto;
import com.align.web.dto.UserProfileDto;
import com.align.web.response.RestApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.TestUtil;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserControllerTest {
  @InjectMocks
  private UserController userController;
  @Mock
  private UserService userService;

  @Mock
  private AuthService authService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void loginTest() {
    when(authService.loginAndAuthorize(anyString(), anyString())).thenReturn(TestUtil.mockUserProfileDto());

    RestApiResponse<UserProfileDto> response = userController.login("admin", "123");

    Assertions.assertNull(response.getError());
    Assertions.assertEquals("OK", response.getMessage());
  }

  @Test
  public void registerTest() {
    when(userService.register(any())).thenReturn(TestUtil.mockUserEntity());

    RestApiResponse<UserDto> response = userController.register(TestUtil.mockUserDto());

    Assertions.assertNull(response.getError());
    Assertions.assertEquals(Constants.REGISTRATION_SUCCEED, response.getMessage());
  }
}
