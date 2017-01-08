package com.nikoyuwono.teamwork.util;

import java.util.Random;

public class RandomString {

  private static final char[] symbols;

  static {
    StringBuilder tmp = new StringBuilder();
    for (char ch = '0'; ch <= '9'; ++ch)
      tmp.append(ch);
    for (char ch = 'a'; ch <= 'z'; ++ch)
      tmp.append(ch);
    symbols = tmp.toString().toCharArray();
  }   

  private final Random random = new Random();

  public String getRandomString(int length) {
    final char[] buffer = new char[length];
    for (int idx = 0; idx < buffer.length; ++idx)
        buffer[idx] = symbols[random.nextInt(symbols.length)];
    return new String(buffer);
  }
}