package pkg5leetcode.official75;

/*
 * Maximum Twin Sum of a Linked List | LC 2130
 * APPROACH: Find mid, reverse second half, max pair sum.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC2130MaximumTwinSumOfALinkedList {
    /** Same shape as pkg5leetcode/common/ListNode.java (nested for single-file runs). */

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static int pairSum(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) { slow = slow.next; fast = fast.next.next; }
        ListNode second = reverse(slow);
        int best = 0;
        ListNode a = head, b = second;
        while (b != null) {
            best = Math.max(best, a.val + b.val);
            a = a.next; b = b.next;
        }
        return best;
    }

    static ListNode reverse(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode n = cur.next; cur.next = prev; prev = cur; cur = n;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode h = new ListNode(5); h.next = new ListNode(4); h.next.next = new ListNode(2);
        h.next.next.next = new ListNode(1);
        check(pairSum(h) == 6, "case1");
        ListNode h2 = new ListNode(4); h2.next = new ListNode(2); h2.next.next = new ListNode(4);
        h2.next.next.next = new ListNode(3);
        check(pairSum(h2) == 7, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
