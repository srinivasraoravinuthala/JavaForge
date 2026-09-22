package pkg5leetcode.interview150;

/*
 * Middle of the Linked List | LC 876
 * APPROACH: Slow/fast pointers; slow at middle when fast reaches end.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC876MiddleOfTheLinkedList {
    /** Same shape as pkg5leetcode/common/ListNode.java (nested for single-file runs). */

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static ListNode of(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    public static void main(String[] args) {
        check(middleNode(of(1, 2, 3, 4, 5)).val == 3, "case1");
        check(middleNode(of(1, 2, 3, 4, 5, 6)).val == 4, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
