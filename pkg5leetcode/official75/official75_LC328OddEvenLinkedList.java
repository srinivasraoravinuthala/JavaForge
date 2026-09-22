package pkg5leetcode.official75;

/*
 * Odd Even Linked List | LC 328
 * APPROACH: Two chains for odd/even indices then connect.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC328OddEvenLinkedList {
    /** Same shape as pkg5leetcode/common/ListNode.java (nested for single-file runs). */

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    static int[] toArray(ListNode head) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        while (head != null) { list.add(head.val); head = head.next; }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        ListNode h = new ListNode(1); h.next = new ListNode(2); h.next.next = new ListNode(3);
        h.next.next.next = new ListNode(4); h.next.next.next.next = new ListNode(5);
        check(java.util.Arrays.equals(toArray(oddEvenList(h)), new int[]{1,3,5,2,4}), "case1");
        ListNode h2 = new ListNode(2); h2.next = new ListNode(1); h2.next.next = new ListNode(3);
        h2.next.next.next = new ListNode(5); h2.next.next.next.next = new ListNode(6);
        h2.next.next.next.next.next = new ListNode(4); h2.next.next.next.next.next.next = new ListNode(7);
        check(java.util.Arrays.equals(toArray(oddEvenList(h2)), new int[]{2,3,6,7,1,5,4}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
