package pkg5leetcode.blind75;

/*
 * Reverse Linked List | LC 206
 * APPROACH: Iterative three-pointer reversal.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class blind75_LC206ReverseLinkedList {
    /** Same shape as pkg5leetcode/common/ListNode.java (nested for single-file runs). */

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static ListNode reverseList(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        return prev;
    }

    static int[] toArray(ListNode head) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        while (head != null) { list.add(head.val); head = head.next; }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1); a.next = new ListNode(2); a.next.next = new ListNode(3);
        check(java.util.Arrays.equals(toArray(reverseList(a)), new int[]{3, 2, 1}), "case1");
        ListNode b = new ListNode(1);
        check(java.util.Arrays.equals(toArray(reverseList(b)), new int[]{1}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
