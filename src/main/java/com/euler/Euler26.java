package com.euler;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions
 * with denominators 2 to 10 are given:
 * <p>
 * 1/2	= 	0.5 <br> 1/3	= 	0.(3) <br> 1/4	= 	0.25 <br> 1/5	= 	0.2 <br> 1/6	= 	0.1(6) <br> 1/7	=
 * 0.(142857) <br> 1/8	= 	0.125 <br> 1/9	= 	0.(1) <br> 1/10	= 	0.1 <br>
 * <p>
 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a
 * 6-digit recurring cycle.
 * <p>
 * Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal
 * fraction part.
 */
public class Euler26 {

  public static void main(String[] args) {
    int max = 0, index = 0;

    for (int i = 1; i <= 1000; i++) {
      int length = lrc(String.valueOf(1 / (double) i));
      if (max < length) {
        index = i;
        max = length;
      }
    }
    System.out.println(index + " " + max);
    System.out.println(BigDecimal.valueOf(1)
        .divide(BigDecimal.valueOf(index), 1100, RoundingMode.HALF_UP));
  }

  static int lrc(String str) {
    str = str.substring(2);
    int start = 0;
    int max = str.length();

    for (int i = start; i < max - 1; i++) {
      char cur = str.charAt(i);
      char cur1 = str.charAt(i + 1);
      int length = 1;

      for (int j = i + 1; j < max - 1; j++) {
        char next = str.charAt(j);

        if (cur == next && cur1 == str.charAt(j + 1)) {
          return length;
        } else {
          length++;
        }
      }
    }
    return 0;
  }

  private static class Pair implements Comparable {

    final int k;
    final String v;

    Pair(int k, String v) {
      this.k = k;
      this.v = v;
    }

    @Override
    public int compareTo(Object o) {
      Pair pair = (Pair) o;
      return this.v.compareTo(pair.v);
    }
  }
}
