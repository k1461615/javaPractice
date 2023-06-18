package leetcode.q49;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class Solution {

  public static List<List<String>> groupAnagrams(String[] strs) {
    HashMap<String, List<String>> input = new HashMap<>();
    for (String str : strs) {
      String sortedStr = sort(str);
      List<String> anagrams = input.computeIfAbsent(sortedStr, k -> new LinkedList<>());
      anagrams.add(str);
    }

    return new ArrayList<>(input.values());
  }

  private static String sort(String str) {
    char[] ca = str.toCharArray();
    Arrays.sort(ca);
    return String.valueOf(ca);
  }
}