# Collections Framework — Interview Questions (120+)

## Detailed Questions

### 1. Overview of the Collections hierarchy?
- **Short:** `Iterable` → `Collection` → List/Set/Queue; `Map` is separate.
- **Detailed:** `Collection` defines add/remove/contains. `List` (ordered, indexed, duplicates), `Set` (unique), `Queue/Deque` (FIFO/LIFO). `Map` (key→value) is not a `Collection`. Implementations: `ArrayList`, `LinkedList`, `HashSet`, `TreeSet`, `HashMap`, `TreeMap`, `ArrayDeque`, `PriorityQueue`.
- **Example:** `List<Integer> l = new ArrayList<>();`

### 2. ArrayList vs LinkedList?
- **Short:** ArrayList = array (fast random access); LinkedList = nodes (fast head/tail ops).
- **Detailed:** ArrayList: O(1) get, O(n) middle insert/remove, cache-friendly, amortized O(1) append. LinkedList: O(1) add/remove at ends, O(n) get, more memory per node. In practice ArrayList wins for most workloads.
- **Example:** Use `ArrayDeque` over LinkedList for stack/queue.

### 3. How does HashMap work internally?
- **Short:** Array of buckets; index from hash; chaining; treeify on heavy collisions.
- **Detailed:** Key's `hashCode` is spread (`h ^ (h>>>16)`) and masked to a bucket. Collisions form a linked list; since Java 8, a bucket with >8 entries (and table ≥64) becomes a red-black tree (O(log n)). Resizes when size > capacity×loadFactor (0.75), doubling capacity and rehashing.
- **Example:** Poor `hashCode` → all in one bucket → O(n) lookups.

### 4. HashMap vs Hashtable vs ConcurrentHashMap?
- **Short:** HashMap (not synced, allows null), Hashtable (legacy, fully synced), ConcurrentHashMap (scalable concurrency).
- **Detailed:** Hashtable locks the whole map (slow). ConcurrentHashMap uses bucket-level locking/CAS, no null keys/values, scales with cores. HashMap allows one null key and null values but isn't thread-safe.
- **Example:** Shared cache → `ConcurrentHashMap`.

### 5. HashSet vs TreeSet vs LinkedHashSet?
- **Short:** Hash=unordered O(1); Tree=sorted O(log n); Linked=insertion order.
- **Detailed:** HashSet backed by HashMap. TreeSet backed by red-black tree (needs Comparable/Comparator), supports range ops (`headSet`, `floor`). LinkedHashSet preserves insertion order.
- **Example:** Need sorted unique → TreeSet.

### 6. HashMap vs TreeMap vs LinkedHashMap?
- **Short:** Same trade-offs as the Set variants.
- **Detailed:** TreeMap keeps keys sorted (NavigableMap: `firstKey`, `ceilingKey`, `subMap`). LinkedHashMap can be access-ordered (great for LRU caches via `removeEldestEntry`).
- **Example:** LRU cache → `LinkedHashMap(accessOrder=true)`.

### 7. How to make a collection thread-safe?
- **Short:** Use concurrent collections or `Collections.synchronizedX`.
- **Detailed:** Prefer `ConcurrentHashMap`, `CopyOnWriteArrayList`, `BlockingQueue`. `Collections.synchronizedList` wraps with a single lock (still need manual sync when iterating).
- **Example:** `List<T> s = Collections.synchronizedList(new ArrayList<>());`

### 8. fail-fast vs fail-safe iterators?
- **Short:** fail-fast throws CME on concurrent modification; fail-safe iterates a snapshot.
- **Detailed:** ArrayList/HashMap iterators are fail-fast (modCount check). CopyOnWriteArrayList and ConcurrentHashMap are fail-safe (weakly consistent).
- **Example:** Remove during loop with `Iterator.remove()` to avoid CME.

### 9. What is the load factor and capacity?
- **Short:** Capacity = bucket count; load factor = fill threshold (0.75).
- **Detailed:** Higher load factor saves memory but increases collisions; lower reduces collisions but wastes space. Size init capacity to avoid rehashing if you know the size.
- **Example:** `new HashMap<>(expected/0.75 + 1)`.

### 10. Why must map keys be immutable / have stable hashCode?
- **Short:** Changing a key's hash after insertion makes it unfindable.
- **Detailed:** The entry sits in a bucket based on the original hash; mutating fields used by hashCode/equals breaks lookups.
- **Example:** Don't use a mutable object whose fields change as a HashMap key.

### 11. Comparable vs Comparator (collections context)?
- **Short:** Natural ordering vs custom/multiple orderings.
- **Detailed:** TreeSet/TreeMap and `Collections.sort` use Comparable by default; pass a Comparator to override. Compose with `comparing`, `thenComparing`, `reversed`, `nullsFirst`.
- **Example:** `list.sort(Comparator.comparingInt(String::length));`

### 12. How does ConcurrentHashMap achieve concurrency?
- **Short:** Bucket-level CAS + synchronized bins; no global lock.
- **Detailed:** Java 8+ uses per-bin synchronization and CAS for updates; reads are mostly lock-free. `compute`, `merge`, `computeIfAbsent` are atomic per key.
- **Example:** `map.merge(key, 1, Integer::sum)` for atomic counters.

### 13. What is CopyOnWriteArrayList good for?
- **Short:** Read-heavy, rarely-written shared lists.
- **Detailed:** Every write copies the array; iterators see an immutable snapshot (no CME). Expensive writes, cheap concurrent reads.
- **Example:** Listener lists.

### 14. What are BlockingQueues?
- **Short:** Thread-safe queues that block on full/empty.
- **Detailed:** `ArrayBlockingQueue` (bounded), `LinkedBlockingQueue`, `PriorityBlockingQueue`, `SynchronousQueue`, `DelayQueue`. Core to producer/consumer and thread pools.
- **Example:** `queue.put(x)` blocks if full; `queue.take()` blocks if empty.

### 15. How to remove elements safely while iterating?
- **Short:** Use `Iterator.remove()` or `removeIf`.
- **Detailed:** Structural modification through the collection during a for-each causes CME. `removeIf(predicate)` is concise and safe.
- **Example:** `list.removeIf(x -> x % 2 == 0);`

---

## Rapid-Fire (Q → A)

1. Is Map a Collection? → No.
2. Root interface of collections? → Iterable.
3. Ordered + duplicates? → List.
4. Unique elements? → Set.
5. Key-value? → Map.
6. FIFO? → Queue. LIFO? → Deque/stack.
7. Default ArrayList capacity? → 10 (lazy).
8. ArrayList growth? → ~1.5×.
9. ArrayList get complexity? → O(1).
10. ArrayList add at index? → O(n).
11. LinkedList get(i)? → O(n).
12. LinkedList implements? → List and Deque.
13. Best stack/queue impl? → ArrayDeque.
14. Is ArrayDeque thread-safe? → No.
15. PriorityQueue order? → Min-heap.
16. PriorityQueue peek/poll? → O(1)/O(log n).
17. HashSet backed by? → HashMap.
18. TreeSet backed by? → TreeMap (red-black tree).
19. TreeSet ordering needs? → Comparable/Comparator.
20. NavigableSet methods? → floor, ceiling, higher, lower.
21. LinkedHashSet preserves? → Insertion order.
22. HashMap null keys? → One allowed.
23. Hashtable null keys? → Not allowed.
24. ConcurrentHashMap null? → No null keys/values.
25. Default load factor? → 0.75.
26. Default capacity? → 16.
27. Treeify threshold? → 8 (with table ≥ 64).
28. Untreeify threshold? → 6.
29. Hash spreading formula? → h ^ (h >>> 16).
30. Why power-of-two capacity? → Fast modulo via bitmask.
31. Resize cost? → O(n) rehash.
32. TreeMap complexity? → O(log n).
33. NavigableMap methods? → firstKey, ceilingKey, subMap.
34. EnumMap? → Array-backed, very fast, enum keys.
35. EnumSet? → Bit-vector set of enums.
36. IdentityHashMap? → Uses == not equals.
37. WeakHashMap? → Keys GC'd when weakly reachable.
38. Properties extends? → Hashtable.
39. Collections.emptyList()? → Immutable empty list.
40. Collections.singletonList()? → Immutable one-element list.
41. List.of mutability? → Immutable.
42. Arrays.asList mutability? → Fixed-size view.
43. Convert array→list? → Arrays.asList / Arrays.stream.
44. Convert list→array? → list.toArray(new T[0]).
45. Sort a list? → Collections.sort / list.sort.
46. Reverse a list? → Collections.reverse.
47. Shuffle? → Collections.shuffle.
48. Binary search list? → Collections.binarySearch (sorted).
49. Min/max? → Collections.min/max.
50. Frequency? → Collections.frequency.
51. Unmodifiable wrapper? → Collections.unmodifiableX.
52. Synchronized wrapper? → Collections.synchronizedX.
53. CME stands for? → ConcurrentModificationException.
54. modCount role? → Detects structural mods for fail-fast.
55. removeIf? → Predicate-based safe removal.
56. replaceAll on list? → Applies UnaryOperator.
57. computeIfAbsent use? → Lazy default per key.
58. merge use? → Combine existing+new value.
59. getOrDefault? → Value or fallback.
60. putIfAbsent? → Insert only if missing.
61. entrySet vs keySet? → Pairs vs keys; entrySet faster for iteration.
62. Map.Entry? → Key-value pair view.
63. Iterate map? → for(var e: map.entrySet()).
64. Immutable map? → Map.of / Map.copyOf.
65. Map.of limit? → 10 pairs (use ofEntries beyond).
66. Duplicate key in Map.of? → IllegalArgumentException.
67. Null in List.of? → NPE.
68. Capacity vs size? → Allocated buckets vs elements.
69. Shrink ArrayList? → trimToSize().
70. ensureCapacity? → Pre-allocate to avoid resizes.
71. SubList view? → list.subList(a,b) (backed view).
72. ListIterator extras? → add, set, previous.
73. Spliterator? → Parallel-friendly traversal.
74. Stream from collection? → collection.stream().
75. Parallel stream pool? → Common ForkJoinPool.
76. CopyOnWriteArraySet? → Set variant of COW list.
77. ConcurrentSkipListMap? → Concurrent sorted map.
78. ConcurrentLinkedQueue? → Lock-free unbounded queue.
79. LinkedBlockingQueue bound? → Optional capacity.
80. SynchronousQueue? → Zero capacity handoff.
81. DelayQueue? → Elements available after delay.
82. BlockingDeque? → Double-ended blocking queue.
83. offer vs add? → offer returns false vs throws on capacity.
84. poll vs remove? → poll returns null vs throws on empty.
85. peek vs element? → peek null vs throws on empty.
86. push/pop on Deque? → Stack ops at head.
87. Why ArrayDeque > Stack? → No legacy sync, faster.
88. Why ArrayList > Vector? → No legacy sync.
89. Iterating + modifying fix? → Iterator.remove / removeIf / collect new.
90. HashMap thread-safe alt? → ConcurrentHashMap.
91. Sorted thread-safe map? → ConcurrentSkipListMap.
92. TreeMap null key? → NPE (natural ordering).
93. LinkedHashMap LRU? → accessOrder + removeEldestEntry.
94. Best for counting frequencies? → HashMap + merge.
95. Best for top-K? → PriorityQueue.
96. Best for dedup preserving order? → LinkedHashSet.
97. Best for range queries? → TreeMap/TreeSet.
98. Initial-size a HashMap? → expected/0.75 + 1.
99. Memory: ArrayList vs LinkedList? → LinkedList heavier (node overhead).
100. Why immutable collections? → Safety, sharing, simplicity.
101. equals/hashCode for keys? → Required for correct lookups.
102. What breaks a HashSet? → Mutating elements' hash after add.
103. contains() on list? → O(n).
104. contains() on HashSet? → O(1) average.
105. retainAll? → Intersection.
106. removeAll? → Difference.
107. addAll? → Union (with dups for list).
108. disjoint? → Collections.disjoint.
109. nCopies? → Immutable repeated list.
110. Collectors.toUnmodifiableList? → Immutable result.
111. groupingBy returns? → Map of lists.
112. partitioningBy returns? → Map<Boolean, List>.
113. toMap merge param? → Resolve duplicate keys.
114. counting collector? → Frequency per group.
115. Best concurrent counter map? → ConcurrentHashMap + merge/atomic.
116. Weakly consistent iterator? → ConcurrentHashMap's.
117. Fail-safe cost? → Snapshot memory/staleness.
118. Capacity tuning benefit? → Fewer rehashes.
119. Streaming a map? → map.entrySet().stream().
120. Choosing a collection rule? → By access pattern: order, uniqueness, sorting, concurrency.
