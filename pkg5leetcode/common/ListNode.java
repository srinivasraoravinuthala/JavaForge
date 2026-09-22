/**
 * Shared ListNode for linked-list problems.
 *
 * Learn once, reuse across Blind75 / Interview 150 / Top 100.
 *
 * Compile with a problem file:
 *   javac pkg5leetcode/common/ListNode.java pkg5leetcode/blind75/YourProblem.java
 *   java -cp pkg5leetcode/common;pkg5leetcode/blind75 YourProblem
 *
 * Or study this shape and keep a nested copy for single-file `java YourProblem.java` runs.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /** Build a list from values, e.g. of(1, 2, 3). */
    public static ListNode of(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : values) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            if (sb.length() > 0) sb.append(" -> ");
            sb.append(cur.val);
            cur = cur.next;
        }
        return sb.toString();
    }
}
