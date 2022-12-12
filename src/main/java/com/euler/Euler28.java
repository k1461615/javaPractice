package com.euler;

/**
 * Starting with the number 1 and moving to the right in a clockwise direction
 * a 5 by 5 spiral is formed as follows:
 * <p>
 * 21 22 23 24 25
 * 20  7  8  9 10
 * 19  6  1  2 11
 * 18  5  4  3 12
 * 17 16 15 14 13
 * <p>
 * It can be verified that the sum of the numbers on the diagonals is 101.
 * <p>
 * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
 */
public class Euler28 {

  public static void main(String[] args) {
    int sideLength = 1001;
    int max = sideLength * sideLength;
    int toAdd = 2;
    int sum = 1;

    for (int i = 1; i < max;) {
      for (int j = 1; j <= 4; j++) {
        i += toAdd;
        sum += i;
      }
      toAdd += 2;
    }
    System.out.println(sum);
  }
}
