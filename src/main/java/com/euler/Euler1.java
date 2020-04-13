package com.euler;

class Euler1 {

  public static void main(String[] args) {
    int end = 1000;
    int answer = 0;

    for (int i = 1; i < end; i++) {
      if ((i % 3 == 0) || (i % 5 == 0)) {
        answer += i;
      }
    }

    System.out.println(answer);
  }
}
