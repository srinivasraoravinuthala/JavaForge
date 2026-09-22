package pkg5leetcode.official75;

/*
 * Delete the Middle Node of a Linked List | LC 2095
 * APPROACH: Slow/fast pointers; delete node after slow.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC2095DeleteTheMiddleNodeOfALinkedList {
    /** Same shape as pkg5leetcode/common/ListNode.java (nested for single-file runs). */

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static ListNode deleteMiddle(ListNode head) {
        if (head.next == null) return null;
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = slow.next;
        return head;
    }

    static int[] toArray(ListNode head) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        while (head != null) { list.add(head.val); head = head.next; }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        ListNode h = new ListNode(1); h.next = new ListNode(3); h.next.next = new ListNode(4);
        h.next.next.next = new ListNode(7); h.next.next.next.next = new ListNode(1);
        h.next.next.next.next.next = new ListNode(2); h.next.next.next.next.next.next = new ListNode(6);
        check(java.util.Arrays.equals(toArray(deleteMiddle(h)), new int[]{1,3,4,1,2,6}), "case1");
        ListNode s = new ListNode(1); s.next = new ListNode(2); s.next.next = new ListNode(3); s.next.next.next = new ListNode(4);
        check(java.util.Arrays.equals(toArray(deleteMiddle(s)), new int[]{1,2,4}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
