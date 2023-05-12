package utils;

import com.align.utils.AlgorithmUtil;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class AlgorithmUtilTest {
  private static final String orginalString = "abcdefg";
  private static final String expectedSha256Value = "7d1a54127b222502f5b79b5fb0803061152a44f92b37e23c6527baf665d4da9a";

  @Test
  public void sha256Test() throws NoSuchAlgorithmException {
    String encoded = AlgorithmUtil.sha256(orginalString);
    Assert.assertEquals(expectedSha256Value, encoded);
  }
}
