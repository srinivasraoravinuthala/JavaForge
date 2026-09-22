package pkg5leetcode.interview150;

/*
 * Palindrome Linked List | LC 234
 * APPROACH: Find middle, reverse second half, compare halves.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC234PalindromeLinkedList {
    /** Same shape as pkg5leetcode/common/ListNode.java (nested for single-file runs). */

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode second = reverse(slow.next);
        slow.next = null;
        ListNode a = head, b = second;
        while (b != null) {
            if (a.val != b.val) return false;
            a = a.next;
            b = b.next;
        }
        return true;
    }

    static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    static ListNode of(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    public static void main(String[] args) {
        check(isPalindrome(of(1, 2, 2, 1)), "case1");
        check(!isPalindrome(of(1, 2)), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
