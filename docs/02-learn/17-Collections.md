# 17 — Collections

**Previous:** [16 Exceptions](16-Exceptions.md) · **Next:** [18 Generics](18-Generics.md)

▶️ `java pkg1core/core19CollectionsDemo.java` · `java pkg1core/core28ComparatorDemo.java`

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
