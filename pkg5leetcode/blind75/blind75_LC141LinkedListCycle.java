package pkg5leetcode.blind75;

/*
 * Linked List Cycle | LC 141
 * APPROACH: Floyd slow/fast pointers detect cycle.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class blind75_LC141LinkedListCycle {
    /** Same shape as pkg5leetcode/common/ListNode.java (nested for single-file runs). */

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode n3 = new ListNode(3);
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = n3;
        n3.next = head.next;
        check(hasCycle(head), "case1");
        ListNode solo = new ListNode(1);
        check(!hasCycle(solo), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
