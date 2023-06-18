package leetcode.q13;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

  static Map<Character, Integer> ROMAN_NUMERALS = new HashMap<>(7);

  static {
    ROMAN_NUMERALS.put('I', 1);
    ROMAN_NUMERALS.put('V', 5);
    ROMAN_NUMERALS.put('X', 10);
    ROMAN_NUMERALS.put('L', 50);
    ROMAN_NUMERALS.put('C', 100);
    ROMAN_NUMERALS.put('D', 500);
    ROMAN_NUMERALS.put('M', 1000);
  }

  public static int romanToInt(String input) {
    int sum = 0;

    for (int i = 0; i < input.length(); i++) {
      char current = input.charAt(i);

      if (i < input.length() - 1
          && (ROMAN_NUMERALS.get(current) < ROMAN_NUMERALS.get(input.charAt(i + 1)))) {
        sum -= ROMAN_NUMERALS.get(current);
      } else {
        sum += ROMAN_NUMERALS.get(current);
      }
    }

    return sum;
  }
}
