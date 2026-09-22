package pkg3datastructures;

/*
 * datastructures2DoublyLinkedList.java
 * ---------------------
 * Each node has prev + next pointers, enabling O(1) insert/remove at both ends
 * and backward traversal.
 *
 * COMPLEXITY: addFirst/addLast/removeFirst/removeLast O(1); search O(n).
 * WHEN TO USE: deques, LRU caches, when you need bidirectional traversal.
 */
public class datastructures2DoublyLinkedList {

    static class Node {
        int val; Node prev, next;
        Node(int val) { this.val = val; }
    }

    private Node head, tail;
    private int size;

    void addFirst(int v) {
        Node n = new Node(v);
        if (head == null) { head = tail = n; }
        else { n.next = head; head.prev = n; head = n; }
        size++;
    }

    void addLast(int v) {
        Node n = new Node(v);
        if (tail == null) { head = tail = n; }
        else { n.prev = tail; tail.next = n; tail = n; }
        size++;
    }

    Integer removeFirst() {
        if (head == null) return null;
        int v = head.val;
        head = head.next;
        if (head == null) tail = null; else head.prev = null;
        size--; return v;
    }

    Integer removeLast() {
        if (tail == null) return null;
        int v = tail.val;
        tail = tail.prev;
        if (tail == null) head = null; else tail.next = null;
        size--; return v;
    }

    String forward() {
        StringBuilder sb = new StringBuilder("[");
        for (Node c = head; c != null; c = c.next) sb.append(c.val).append(c.next != null ? " <-> " : "");
        return sb.append("]").toString();
    }

    String backward() {
        StringBuilder sb = new StringBuilder("[");
        for (Node c = tail; c != null; c = c.prev) sb.append(c.val).append(c.prev != null ? " <-> " : "");
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        datastructures2DoublyLinkedList dll = new datastructures2DoublyLinkedList();
        dll.addLast(2); dll.addLast(3); dll.addFirst(1); dll.addLast(4);
        System.out.println("forward:  " + dll.forward() + " size=" + dll.size);
        System.out.println("backward: " + dll.backward());
        System.out.println("removeFirst=" + dll.removeFirst() + " removeLast=" + dll.removeLast());
        System.out.println("after removals: " + dll.forward());
    }
}
