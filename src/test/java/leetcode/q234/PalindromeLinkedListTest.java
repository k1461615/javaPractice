package leetcode.q234;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PalindromeLinkedListTest {

  @Test
  void isPalindromeTest() {

    ListNode input = new ListNode(1,
        new ListNode(2,
            new ListNode(2,
                new ListNode(1))));

    assertTrue(PalindromeLinkedList.isPalindrome(input));

    input = new ListNode(1,
        new ListNode(2,
            new ListNode(5,
                new ListNode(2,
                    new ListNode(1)))));

    assertTrue(PalindromeLinkedList.isPalindrome(input));
  }
}