package service;

import com.align.persistence.entity.UserEntity;
import com.align.persistence.repository.UserRepository;
import com.align.service.AuthService;
import com.align.service.UserService;
import com.align.web.dto.UserProfileDto;
import com.align.web.exceptions.CommonException;
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

public class UserServiceTest {
  @InjectMocks
  private UserService userService;
  @Mock
  private UserRepository userRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void loginTest() {
    UserEntity userEntity = TestUtil.mockUserEntity();

    when(userRepository.findByNameOrEmailAndPassword(anyString(), anyString())).thenReturn(userEntity);

    UserEntity entity = userService.login("admin", "123");

    Assertions.assertNotNull(entity);
  }

  @Test
  public void registrationWithNameExistsTest() {
    when(userRepository.findByName(anyString())).thenReturn(TestUtil.mockUserEntity());

    Assertions.assertThrows(CommonException.class, () -> userService.register(TestUtil.mockUserDto()));
  }

  @Test
  public void registrationWithEmailExistsTest() {
    when(userRepository.findByName(anyString())).thenReturn(null);
    when(userRepository.findByEmail(anyString())).thenReturn(TestUtil.mockUserEntity());

    Assertions.assertThrows(CommonException.class, () -> userService.register(TestUtil.mockUserDto()));
  }

  @Test
  public void registrationExceptionTest() {
    when(userRepository.findByName(anyString())).thenReturn(null);
    when(userRepository.findByEmail(anyString())).thenReturn(null);
    when(userRepository.save(any())).thenThrow(new CommonException(""));

    Assertions.assertThrows(CommonException.class, () -> userService.register(TestUtil.mockUserDto()));
  }

  @Test
  public void registrationTest() {
    when(userRepository.findByName(anyString())).thenReturn(null);
    when(userRepository.findByEmail(anyString())).thenReturn(null);
    when(userRepository.save(any())).thenReturn(null);

    userService.register(TestUtil.mockUserDto());
  }
}
