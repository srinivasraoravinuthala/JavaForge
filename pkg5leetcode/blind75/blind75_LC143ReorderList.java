package pkg5leetcode.blind75;

/*
 * Reorder List | LC 143
 * APPROACH: Find middle, reverse second half, merge alternating.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class blind75_LC143ReorderList {
    /** Same shape as pkg5leetcode/common/ListNode.java (nested for single-file runs). */

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode second = reverse(slow.next);
        slow.next = null;
        ListNode a = head, b = second;
        while (b != null) {
            ListNode an = a.next, bn = b.next;
            a.next = b;
            b.next = an;
            a = an;
            b = bn;
        }
    }

    static ListNode reverse(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        return prev;
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
        ListNode h = of(1, 2, 3, 4);
        reorderList(h);
        check(java.util.Arrays.equals(toArray(h), new int[]{1, 4, 2, 3}), "case1");
        ListNode h2 = of(1, 2);
        reorderList(h2);
        check(java.util.Arrays.equals(toArray(h2), new int[]{1, 2}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
