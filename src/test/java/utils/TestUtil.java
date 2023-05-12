package utils;

import com.align.persistence.entity.PostsEntity;
import com.align.persistence.entity.UserEntity;
import com.align.web.dto.PostsDto;
import com.align.web.dto.UserDto;
import com.align.web.dto.UserFollowerDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestUtil {
  public static UserEntity mockUserEntity() {
    UserEntity userEntity = new UserEntity();
    userEntity.setId(1L);
    userEntity.setName("tester");
    userEntity.setEmail("hk@h.com");
    return userEntity;
  }

  public static UserDto mockUserDto() {
    UserDto userDto = new UserDto();
    userDto.setName("tester");
    userDto.setEmail("hk@h.com");
    userDto.setPassword("abc");
    return userDto;
  }

  public static PostsDto mockPostDto() {
    PostsDto post = new PostsDto();
    post.setPosterId(1L);
    post.setPosterName("jack");
    post.setMessage("message");
    return post;
  }

  public static List<PostsEntity> mockPostEntity() {
    PostsEntity entity = new PostsEntity();
    entity.setId(111L);
    entity.setPosterId(1L);
    entity.setPosterName("mack");
    entity.setMessage("mocked message");
    List<PostsEntity> list = new ArrayList<>();
    list.add(entity);
    return list;
  }

  public static List<UserFollowerDto> mockUserFollowers() {
    List<UserFollowerDto> list = new ArrayList<>();
    UserFollowerDto u = new UserFollowerDto();
    u.setUserId(1L);
    u.setUsername("jack");
    u.setUserEmail("email");
    list.add(u);
    return list;
  }
}
