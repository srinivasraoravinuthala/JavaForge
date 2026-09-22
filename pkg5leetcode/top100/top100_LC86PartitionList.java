package pkg5leetcode.top100;

/*
 * Partition List | LC 86
 * APPROACH: Two dummy lists for <x and >=x; concatenate.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class top100_LC86PartitionList {
    /** Same shape as pkg5leetcode/common/ListNode.java (nested for single-file runs). */

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static ListNode partition(ListNode head, int x) {
        ListNode before = new ListNode(0), after = new ListNode(0);
        ListNode b = before, a = after;
        while (head != null) {
            if (head.val < x) { b.next = head; b = b.next; }
            else { a.next = head; a = a.next; }
            head = head.next;
        }
        a.next = null;
        b.next = after.next;
        return before.next;
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
        check(java.util.Arrays.equals(toArray(partition(of(1, 4, 3, 2, 5, 2), 3)), new int[]{1, 2, 2, 4, 3, 5}), "case1");
        check(java.util.Arrays.equals(toArray(partition(of(2, 1), 2)), new int[]{1, 2}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
