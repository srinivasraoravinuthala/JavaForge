package pkg3datastructures;

/*
 * datastructures1SinglyLinkedList.java
 * ---------------------
 * A singly linked list with add, insert, delete, reverse, and cycle detection.
 *
 * COMPLEXITY:
 *  - addFirst: O(1), addLast: O(n) (O(1) if we track tail), get(i): O(n)
 *  - delete(value): O(n), reverse: O(n)
 *
 * WHEN TO USE: frequent insert/delete at the head; unknown size; no random access.
 */
public class datastructures1SinglyLinkedList {

    static class Node {
        int val; Node next;
        Node(int val) { this.val = val; }
    }

    private Node head;
    private int size;

    void addFirst(int v) { Node n = new Node(v); n.next = head; head = n; size++; }

    void addLast(int v) {
        Node n = new Node(v);
        if (head == null) { head = n; }
        else { Node c = head; while (c.next != null) c = c.next; c.next = n; }
        size++;
    }

    boolean delete(int v) {
        if (head == null) return false;
        if (head.val == v) { head = head.next; size--; return true; }
        Node c = head;
        while (c.next != null && c.next.val != v) c = c.next;
        if (c.next == null) return false;
        c.next = c.next.next; size--; return true;
    }

    // Reverse the list iteratively (classic interview question)
    void reverse() {
        Node prev = null, curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    // Floyd's cycle detection (tortoise & hare)
    boolean hasCycle() {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    int size() { return size; }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Node c = head; c != null; c = c.next) sb.append(c.val).append(c.next != null ? " -> " : "");
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        datastructures1SinglyLinkedList list = new datastructures1SinglyLinkedList();
        list.addLast(1); list.addLast(2); list.addLast(3);
        list.addFirst(0);
        System.out.println("list: " + list + " size=" + list.size());

        list.delete(2);
        System.out.println("after delete(2): " + list);

        list.reverse();
        System.out.println("reversed: " + list);

        System.out.println("hasCycle: " + list.hasCycle());
        // Build a cycle manually to test detection
        list.head.next.next.next = list.head;   // create loop
        System.out.println("hasCycle after creating loop: " + list.hasCycle());
    }
}
