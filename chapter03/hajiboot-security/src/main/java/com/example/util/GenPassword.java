package com.example.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Yoshimasa Tanabe
 */
public class GenPassword {

  private GenPassword() {}

  public static String encode(String password) {
    return new BCryptPasswordEncoder().encode(password);
  }

}
