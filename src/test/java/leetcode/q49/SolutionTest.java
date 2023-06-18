package leetcode.q49;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Stack;
import org.junit.jupiter.api.Test;

class SolutionTest {

  @Test
  void groupAnagrams() {
    String[] in1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
    List<List<String>> output = List.of(
        List.of("bat"),
        List.of("nat", "tan"),
        List.of("ate", "eat", "tea")
    );

//    assertEquals(output, Solution.groupAnagrams(in1));

    String[] in2 = {""};
    List<List<String>> output2 = List.of(List.of(""));

//    assertEquals(output2, Solution.groupAnagrams(in2));
    
    String[] in3 = {"a"};
    List<List<String>> output3 = List.of(List.of("a"));

//    assertEquals(output3, Solution.groupAnagrams(in3));
  }
}