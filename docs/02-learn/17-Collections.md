# 17 — Collections

**Previous:** [16 Exceptions](16-Exceptions.md) · **Next:** [18 Generics](18-Generics.md)

▶️ `java pkg1core/core19CollectionsDemo.java` · `java pkg1core/core29HashMapDemo.java` · `java pkg1core/core28ComparatorDemo.java`

---

## Hierarchy at a glance

```
Iterable
└── Collection
    ├── List      (ordered, duplicates OK)
    ├── Set       (unique)
    └── Queue/Deque

Map (separate — key → value)
```

---

## Pick the right collection

| Need | Use |
|------|-----|
| Indexed list, fast random access | `ArrayList` |
| Unique elements | `HashSet` |
| Sorted unique | `TreeSet` |
| Key-value lookup | `HashMap` |
| Sorted keys | `TreeMap` |
| Insertion order | `LinkedHashMap` / `LinkedHashSet` |
| FIFO queue / LIFO stack | `ArrayDeque` |
| Priority / top-K | `PriorityQueue` |

---

## Quick examples

```java
List<String> list = new ArrayList<>(List.of("b", "a", "c"));
list.sort(String::compareTo);

Map<String, Integer> freq = new HashMap<>();
for (String s : list) freq.merge(s, 1, Integer::sum);

Set<String> unique = new TreeSet<>(list);   // sorted unique

Queue<Integer> q = new ArrayDeque<>();
q.offer(1); q.poll();
```

---

## HashMap

`HashMap` stores **key → value** pairs for fast lookup. Keys are unique: putting the same key again **replaces** the previous value. Values may repeat.

### Common operations

```java
Map<String, Integer> ages = new HashMap<>();
ages.put("Ana", 20);
ages.put("Bob", 19);
ages.get("Ana");                 // 20
ages.getOrDefault("Zed", 0);     // 0 if missing
ages.containsKey("Bob");         // true
ages.put("Ana", 21);             // update existing key
ages.merge("Ana", 1, Integer::sum);  // read-modify-write idiom
```

Average lookup and update are **O(1)** when hashes spread well. Heavy collisions degrade toward O(n) — interview material covers capacity and load factor in depth.

### Null keys and values

`HashMap` allows **one** `null` key and any number of `null` values. Prefer clear keys in new code; treat null entries as a special case when reading older APIs.

### Keys need stable equals and hashCode

A key is found by **bucket** (from `hashCode`) then **equality** (from `equals`). If two objects are equal, their hash codes must match. If you mutate a field used by `equals`/`hashCode` after `put`, the entry can become **unfindable**.

▶️ See `pkg1core/core29HashMapDemo.java` for a small key-contract demo.

### When to choose HashMap

| Choose `HashMap` when… | Prefer something else when… |
|------------------------|-----------------------------|
| You need fast get/put by key | You need keys **sorted** → `TreeMap` |
| Order of entries does not matter | You need **insertion** (or access) order → `LinkedHashMap` |
| One writer / single-threaded use | Shared across threads → `ConcurrentHashMap` (see thread safety below) |

### See it in code

1. **API usage** — frequency maps and Map helpers: `pkg1core/core19CollectionsDemo.java`
2. **Key contract** — equals/hashCode with `java.util.HashMap`: `pkg1core/core29HashMapDemo.java`
3. **Under the hood** — buckets, chaining, resize (teaching reimplementation): `pkg3datastructures/datastructures6HashTableImpl.java`

### Practice (hash-map approaches in this repo)

These solutions document a hash-map technique in their APPROACH comments:

- [Two Sum](../../pkg5leetcode/blind75/blind75_LC1TwoSum.java) — one-pass hash map
- [Group Anagrams](../../pkg5leetcode/blind75/blind75_LC49GroupAnagrams.java) — HashMap by signature
- [Subarray Sum Equals K](../../pkg5leetcode/interview150/interview150_LC560SubarraySumEqualsK.java) — prefix-sum hash map
- [Isomorphic Strings](../../pkg5leetcode/interview150/interview150_LC205IsomorphicStrings.java) — two hash maps
- [Contains Duplicate II](../../pkg5leetcode/interview150/interview150_LC219ContainsDuplicateII.java) — hash map of last index

### Interview bridge

Now that you understand the fundamentals, these interview questions take you deeper (answers stay in the interview hub — do not skip the demos above):

- [How does HashMap work internally?](../03-interview/03-Collections.md#3-how-does-hashmap-work-internally)
- [HashMap vs Hashtable vs ConcurrentHashMap?](../03-interview/03-Collections.md#4-hashmap-vs-hashtable-vs-concurrenthashmap)
- [HashMap vs TreeMap vs LinkedHashMap?](../03-interview/03-Collections.md#6-hashmap-vs-treemap-vs-linkedhashmap)
- [What is the load factor and capacity?](../03-interview/03-Collections.md#9-what-is-the-load-factor-and-capacity)
- [Why must map keys be immutable / have stable hashCode?](../03-interview/03-Collections.md#10-why-must-map-keys-be-immutable-have-stable-hashcode)

---

## Comparable vs Comparator

```java
// Natural order — built into class
class Student implements Comparable<Student> {
    public int compareTo(Student o) { return Integer.compare(score, o.score); }
}

// Custom order — external, flexible
list.sort(Comparator.comparingInt(Student::score).reversed()
                    .thenComparing(Student::name));
```

---

## Thread safety

Default collections are **not** thread-safe. Use:
- `ConcurrentHashMap`
- `CopyOnWriteArrayList`
- `Collections.synchronizedList()` (with care)

**Deep dive →** [03-interview/03-Collections.md](../03-interview/03-Collections.md)

**Next →** [18 Generics](18-Generics.md)
