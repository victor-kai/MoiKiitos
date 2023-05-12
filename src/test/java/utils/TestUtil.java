package utils;

import com.align.persistence.entity.UserEntity;

public class TestUtil {
  public static UserEntity mockUserEntity() {
    UserEntity userEntity = new UserEntity();
    userEntity.setId(1L);
    userEntity.setName("tester");
    userEntity.setEmail("hk@h.com");
    return userEntity;
  }
}
