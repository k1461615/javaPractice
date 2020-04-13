package com.euler;

import java.util.ArrayList;

public class Euler24 {

  public static void main(String[] args) {
    ArrayList<Integer> digits = new ArrayList<>();
    digits.add(0);
    digits.add(1);
    digits.add(2);
    digits.add(3);
    digits.add(4);
    digits.add(5);
    digits.add(6);
    digits.add(7);
    digits.add(8);
    digits.add(9);

    int end = 1000000 - 1;

    StringBuilder answer = new StringBuilder();

    for (int i = digits.size() - 1; i > 0; i--) {
      int fact = fact(i);
      int times = end / fact;

      end -= fact * times;
      answer.append(digits.remove(times));
    }
    answer.append(digits.remove(0));

    System.out.println(answer);
  }

  private static int fact(int i) {
    for (int j = i - 1; j > 0; j--) {
      i *= j;
    }
    return i;
  }

    /*
    1000000
    274240
    32320
    2080
    640
    40
    16
    4
    0
    0


    2783915460




      0123
      0132
      0213
      0231
      0312
      0321
      1023
      1032
      1203
      1230
      1302
      1320
      2013
      2031
      2103
      2130
      3012
      3021
      3102
      3120
      3201
      3210
     */
}
