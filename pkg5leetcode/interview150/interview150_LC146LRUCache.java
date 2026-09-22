package pkg5leetcode.interview150;

/*
 * LRU Cache | LC 146
 * APPROACH: HashMap + doubly linked list for O(1) get/put eviction.
 * COMPLEXITY: Time O(1) per op, Space O(capacity)
 */
import java.util.*;

public class interview150_LC146LRUCache {
    static class LRUCache {
        static class Node {
            int key, val;
            Node prev, next;
            Node(int k, int v) { key = k; val = v; }
        }

        final int cap;
        final Map<Integer, Node> map = new HashMap<>();
        final Node head = new Node(0, 0), tail = new Node(0, 0);

        LRUCache(int capacity) {
            cap = capacity;
            head.next = tail;
            tail.prev = head;
        }

        int get(int key) {
            if (!map.containsKey(key)) return -1;
            Node n = map.get(key);
            remove(n);
            insert(n);
            return n.val;
        }

        void put(int key, int value) {
            if (map.containsKey(key)) {
                Node n = map.get(key);
                n.val = value;
                remove(n);
                insert(n);
            } else {
                if (map.size() == cap) {
                    map.remove(tail.prev.key);
                    remove(tail.prev);
                }
                Node n = new Node(key, value);
                map.put(key, n);
                insert(n);
            }
        }

        void remove(Node n) {
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }

        void insert(Node n) {
            n.next = head.next;
            n.prev = head;
            head.next.prev = n;
            head.next = n;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        check(cache.get(1) == 1, "case1");
        cache.put(3, 3);
        check(cache.get(2) == -1, "case2");
        cache.put(4, 4);
        check(cache.get(1) == -1 && cache.get(3) == 3 && cache.get(4) == 4, "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
