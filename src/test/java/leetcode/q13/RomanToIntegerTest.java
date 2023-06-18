package leetcode.q13;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import leetcode.q13.RomanToInteger;
import org.junit.jupiter.api.Test;

class RomanToIntegerTest {
  @Test
  void romanToIntTests(){
    Map<String, Integer> expectedIo = new HashMap<>(3);
    expectedIo.put("III", 3); // III = 3.
    expectedIo.put("LVIII", 58); // L = 50, V= 5, III = 3.
    expectedIo.put("MCMXCIV", 1994); // M = 1000, CM = 900, XC = 90 and IV = 4.

    expectedIo.forEach((input, expected) -> {
      int actual = RomanToInteger.romanToInt(input);
      assertEquals(expected, actual);
    });
  }
}
