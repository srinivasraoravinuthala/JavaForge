package pkg5leetcode.blind75;

/*
 * Merge k Sorted Lists | LC 23
 * APPROACH: Min-heap of list heads by value.
 * COMPLEXITY: Time O(N log k), Space O(k)
 */
import java.util.*;

public class blind75_LC23MergeKSortedLists {
    /** Same shape as pkg5leetcode/common/ListNode.java (nested for single-file runs). */

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) if (node != null) pq.add(node);
        ListNode dummy = new ListNode(0), tail = dummy;
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            tail.next = cur;
            tail = cur;
            if (cur.next != null) pq.add(cur.next);
        }
        return dummy.next;
    }

    static ListNode of(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    static int[] toArray(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) { list.add(head.val); head = head.next; }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        ListNode[] lists = {of(1, 4, 5), of(1, 3, 4), of(2, 6)};
        check(Arrays.equals(toArray(mergeKLists(lists)), new int[]{1, 1, 2, 3, 4, 4, 5, 6}), "case1");
        check(mergeKLists(new ListNode[]{}) == null, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
