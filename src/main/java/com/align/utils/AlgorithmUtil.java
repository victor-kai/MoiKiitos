package com.align.utils;

import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AlgorithmUtil {
  private static final String SHA256 = "SHA-256";

  public static String sha256(String value) throws NoSuchAlgorithmException {
    MessageDigest instance = MessageDigest.getInstance(SHA256);
    instance.update(value.getBytes(StandardCharsets.UTF_8));
    return String.valueOf(HexUtils.toHexString(instance.digest()));
  }
}
