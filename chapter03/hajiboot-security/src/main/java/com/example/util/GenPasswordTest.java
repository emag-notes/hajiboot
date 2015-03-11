package com.example.util;

import org.junit.Test;

public class GenPasswordTest {

  @Test
  public void encode() throws Exception {
    System.out.println(GenPassword.encode("demo"));
  }

}