package leetcode.q234;

public class PalindromeLinkedList {

  public static boolean isPalindrome(ListNode head) {
    int length = 1;
    ListNode c = head;
    while (c.next != null) {
      c = c.next;
      length += 1;
    }

    ListNode endHalf = head;
    for (int i = 0; i < length / 2; i++) {
      endHalf = endHalf.next;
    }

    if (length % 2 == 1) {
      endHalf = endHalf.next;
    }

    ListNode prev = null;
    ListNode next;

    while (endHalf != null) {
      next = endHalf.next;
      endHalf.next = prev;
      prev = endHalf;
      endHalf = next;
    }
    endHalf = prev;

    for (int i = 0; i < length / 2; i++) {
      if (head.val != endHalf.val) {
        return false;
      }
      head = head.next;
      endHalf = endHalf.next;
    }

    return true;
  }
}
