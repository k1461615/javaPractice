package com.euler;

public class Euler15 {

  public static void main(String[] args) {
    System.out.println(gridPaths(20));
  }

  private static long gridPaths(int x) {
    long answer = 1;

    for (int i = 1; i <= x; i++) {
      answer = answer * (x + i) / i;
    }

    return answer;
  }
}
