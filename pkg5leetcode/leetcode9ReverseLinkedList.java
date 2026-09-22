package pkg5leetcode;

/*
 * LeetCode 206: Reverse Linked List  (Easy)
 * -----------------------------------------
 * Reverse a singly linked list. Shown iteratively and recursively.
 *
 * COMPLEXITY: Time O(n); iterative Space O(1), recursive Space O(n) (call stack).
 */
public class leetcode9ReverseLinkedList {

    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static ListNode reverseIterative(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    static ListNode reverseRecursive(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    static ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), tail = dummy;
        for (int v : vals) { tail.next = new ListNode(v); tail = tail.next; }
        return dummy.next;
    }
    static int[] toArray(ListNode head) {
        java.util.List<Integer> out = new java.util.ArrayList<>();
        for (ListNode c = head; c != null; c = c.next) out.add(c.val);
        return out.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        check(java.util.Arrays.equals(toArray(reverseIterative(build(1, 2, 3, 4, 5))), new int[]{5, 4, 3, 2, 1}), "iterative");
        check(java.util.Arrays.equals(toArray(reverseRecursive(build(1, 2, 3))), new int[]{3, 2, 1}), "recursive");
        check(toArray(reverseIterative(build())).length == 0, "empty");
        System.out.println("leetcode9ReverseLinkedList: all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
