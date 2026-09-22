package pkg5leetcode.interview150;

/*
 * Remove Duplicates from Sorted List | LC 83
 * APPROACH: Skip nodes where next has same value.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC83RemoveDuplicatesFromSortedList {
    /** Same shape as pkg5leetcode/common/ListNode.java (nested for single-file runs). */

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) cur.next = cur.next.next;
            else cur = cur.next;
        }
        return head;
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
        check(java.util.Arrays.equals(toArray(deleteDuplicates(of(1, 1, 2))), new int[]{1, 2}), "case1");
        check(java.util.Arrays.equals(toArray(deleteDuplicates(of(1, 1, 2, 3, 3))), new int[]{1, 2, 3}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
