package pkg5leetcode.blind75;

/*
 * Merge Two Sorted Lists | LC 21
 * APPROACH: Dummy head merge two pointers.
 * COMPLEXITY: Time O(n+m), Space O(1)
 */
public class blind75_LC21MergeTwoSortedLists {
    /** Same shape as pkg5leetcode/common/ListNode.java (nested for single-file runs). */

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) { tail.next = l1; l1 = l1.next; }
            else { tail.next = l2; l2 = l2.next; }
            tail = tail.next;
        }
        tail.next = l1 != null ? l1 : l2;
        return dummy.next;
    }

    static ListNode of(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    static int[] toArray(ListNode head) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        while (head != null) { list.add(head.val); head = head.next; }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        check(java.util.Arrays.equals(toArray(mergeTwoLists(of(1, 2, 4), of(1, 3, 4))), new int[]{1, 1, 2, 3, 4, 4}), "case1");
        check(java.util.Arrays.equals(toArray(mergeTwoLists(null, of(0))), new int[]{0}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
