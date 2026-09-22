package pkg1core;

/*
 * core19CollectionsDemo.java
 * --------------------
 * The Collections Framework: List, Set, Map, Queue, Deque, and when to use each.
 *
 * EXPLANATION:
 *  - List: ordered, indexed, allows duplicates (ArrayList / LinkedList).
 *  - Set: no duplicates (HashSet=unordered, LinkedHashSet=insertion, TreeSet=sorted).
 *  - Map: key->value (HashMap, LinkedHashMap, TreeMap).
 *  - Queue/Deque: FIFO/LIFO (ArrayDeque), PriorityQueue=min-heap.
 */
import java.util.*;

public class core19CollectionsDemo {
    public static void main(String[] args) {
        // ---- List ----
        List<String> list = new ArrayList<>(List.of("b", "a", "c", "a"));
        list.add("d");
        Collections.sort(list);
        System.out.println("List (sorted, dups kept): " + list + " get(0)=" + list.get(0));

        // ---- Set (uniqueness) ----
        Set<String> hash = new HashSet<>(list);
        Set<String> tree = new TreeSet<>(list);                 // sorted, unique
        System.out.println("HashSet (unordered, unique): " + hash);
        System.out.println("TreeSet (sorted, unique): " + tree);

        // ---- Map ----
        Map<String, Integer> counts = new HashMap<>();
        for (String s : list) counts.merge(s, 1, Integer::sum); // frequency count idiom
        System.out.println("Frequency map: " + counts);
        System.out.println("getOrDefault('z',0): " + counts.getOrDefault("z", 0));
        counts.computeIfAbsent("e", k -> 0);
        System.out.println("after computeIfAbsent: " + counts);

        // TreeMap keeps keys sorted
        TreeMap<String, Integer> sortedMap = new TreeMap<>(counts);
        System.out.println("TreeMap firstKey=" + sortedMap.firstKey() + " lastKey=" + sortedMap.lastKey());

        // ---- Queue (FIFO) ----
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1); q.offer(2); q.offer(3);
        System.out.println("Queue poll order: " + q.poll() + ", " + q.poll());

        // ---- Deque as Stack (LIFO) ----
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1); stack.push(2); stack.push(3);
        System.out.println("Stack pop order: " + stack.pop() + ", " + stack.pop());

        // ---- PriorityQueue (min-heap) ----
        PriorityQueue<Integer> pq = new PriorityQueue<>(List.of(5, 1, 3, 2, 4));
        StringBuilder order = new StringBuilder();
        while (!pq.isEmpty()) order.append(pq.poll()).append(' ');
        System.out.println("PriorityQueue ascending: " + order.toString().trim());

        // Iteration safety: remove while iterating via Iterator
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Iterator<Integer> it = nums.iterator();
        while (it.hasNext()) if (it.next() % 2 == 0) it.remove();
        System.out.println("After removing evens: " + nums);
    }
}
