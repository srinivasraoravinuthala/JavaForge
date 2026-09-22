# Functional Programming & Streams — Interview Questions (100+)

## Detailed Questions

### 1. What is a Stream and how does it differ from a Collection?
- **Short:** A pipeline for processing data; not storage.
- **Detailed:** A `Collection` stores elements; a `Stream` describes a computation over a source. Streams are lazy, single-use, and can be sequential or parallel. They don't mutate the source.
- **Example:** `list.stream().filter(x->x>0).map(x->x*2).toList();`

### 2. Intermediate vs terminal operations?
- **Short:** Intermediate are lazy and return a Stream; terminal trigger execution.
- **Detailed:** `filter/map/sorted/distinct/limit` are intermediate (build the pipeline). `collect/forEach/reduce/count/findFirst` are terminal (consume it). Without a terminal op, nothing runs.
- **Example:** `stream.filter(...)` alone does nothing until `.toList()`.

### 3. What does "lazy evaluation" mean for streams?
- **Short:** Work happens only when a terminal op runs, element-by-element.
- **Detailed:** Elements flow through the pipeline one at a time; short-circuiting ops (`limit`, `findFirst`, `anyMatch`) can stop early without processing the whole source.
- **Example:** `Stream.iterate(1,x->x+1).filter(...).findFirst()` stops at the first match.

### 4. map vs flatMap?
- **Short:** map: 1→1 transform; flatMap: 1→many, then flatten.
- **Detailed:** `map` applies a function producing one element each. `flatMap` produces a stream per element and concatenates them—used to flatten nested structures.
- **Example:** `lists.stream().flatMap(List::stream)` flattens `List<List<T>>`.

### 5. reduce vs collect?
- **Short:** reduce: immutable fold to one value; collect: mutable reduction into a container.
- **Detailed:** `reduce(identity, accumulator)` combines elements (sum, product). `collect(Collector)` accumulates into lists/maps/strings efficiently (mutable containers, parallel-safe combiners).
- **Example:** `stream.reduce(0,Integer::sum)` vs `stream.collect(toList())`.

### 6. What are Collectors and common ones?
- **Short:** Recipes for `collect`: toList, toMap, groupingBy, joining, counting.
- **Detailed:** Collectors build/merge results: `groupingBy` (Map of groups), `partitioningBy` (boolean split), `mapping`, `counting`, `summingInt`, `averagingDouble`, `joining`, `toUnmodifiableList`.
- **Example:** `people.stream().collect(groupingBy(Person::city, counting()))`.

### 7. When should you use parallel streams?
- **Short:** Large, CPU-bound, stateless, easily-splittable data—after measuring.
- **Detailed:** Parallel streams use the common ForkJoinPool. Good for big data with cheap, independent operations and splittable sources (arrays, ArrayList). Avoid for small data, blocking I/O, stateful/ordered operations, or shared mutable state.
- **Example:** `list.parallelStream().mapToInt(...).sum()`—benchmark vs sequential.

### 8. What is a functional interface?
- **Short:** An interface with exactly one abstract method.
- **Detailed:** Lambdas/method refs target functional interfaces. `@FunctionalInterface` enforces the single-abstract-method rule. Defaults/statics don't count.
- **Example:** `Runnable`, `Comparator`, `Function`, custom `Calculator`.

### 9. Core functional interfaces in java.util.function?
- **Short:** Supplier, Consumer, Function, Predicate, and bi/unary/operator variants.
- **Detailed:** `Supplier<T> get`, `Consumer<T> accept`, `Function<T,R> apply`, `Predicate<T> test`, `BiFunction`, `UnaryOperator`, `BinaryOperator`, plus primitive specializations (`IntFunction`, `ToIntFunction`).
- **Example:** `Predicate<Integer> even = x -> x%2==0;`

### 10. What is the difference between findFirst and findAny?
- **Short:** findFirst respects order; findAny may be faster in parallel.
- **Detailed:** In sequential streams they're equivalent. In parallel, `findAny` can return any matching element (less coordination), while `findFirst` must honor encounter order.
- **Example:** `parallel.filter(...).findAny()`.

### 11. Why are streams single-use?
- **Short:** A stream is consumed by its terminal op.
- **Detailed:** Reusing a consumed stream throws `IllegalStateException`. Create a fresh stream from the source if needed.
- **Example:** Store a `Supplier<Stream<T>>` to recreate.

### 12. How do you avoid side effects in streams?
- **Short:** Use pure functions and collectors, not external mutation.
- **Detailed:** Prefer `collect`/`reduce` over `forEach` that mutates shared state—especially in parallel (data races). Stateless, non-interfering lambdas are required for correctness.
- **Example:** Build a list with `toList()` rather than `forEach(list::add)`.

### 13. Optional best practices?
- **Short:** Return type for "maybe"; chain map/filter; avoid get().
- **Detailed:** Don't use Optional for fields/params/collections. Prefer `orElse/orElseGet/orElseThrow/ifPresent`. Use `flatMap` to avoid nested Optionals.
- **Example:** `find(id).map(User::email).orElse("none");`

### 14. What are primitive streams and why use them?
- **Short:** IntStream/LongStream/DoubleStream avoid boxing.
- **Detailed:** They provide `sum`, `average`, `range`, `summaryStatistics` and prevent autoboxing overhead. Convert with `mapToInt`/`boxed`.
- **Example:** `IntStream.rangeClosed(1,100).sum();`

### 15. teeing and other Java 12+ collectors?
- **Short:** `teeing` combines two collectors' results.
- **Detailed:** `Collectors.teeing(c1, c2, merger)` runs two downstream collectors and merges (e.g. average = sum/count in one pass).
- **Example:** compute min and max together.

---

## Rapid-Fire (Q → A)

1. Create stream from list? → list.stream().
2. From array? → Arrays.stream(arr).
3. From values? → Stream.of(a,b,c).
4. Infinite stream? → Stream.iterate / Stream.generate.
5. Empty stream? → Stream.empty().
6. Range of ints? → IntStream.range / rangeClosed.
7. Count elements? → stream.count().
8. To list? → stream.toList() (Java 16+).
9. To set? → collect(toSet()).
10. To map? → collect(toMap(k,v)).
11. Join strings? → collect(joining(", ")).
12. Sum ints? → mapToInt(...).sum().
13. Average? → mapToInt(...).average().
14. Max? → max(Comparator) / mapToInt().max().
15. Sort? → sorted() / sorted(Comparator).
16. Distinct? → distinct().
17. Limit? → limit(n).
18. Skip? → skip(n).
19. Peek? → peek() (debugging).
20. Map? → map(fn).
21. FlatMap? → flatMap(fn).
22. Filter? → filter(predicate).
23. Reduce? → reduce(identity, acc).
24. anyMatch? → boolean any element matches.
25. allMatch? → boolean all match.
26. noneMatch? → boolean none match.
27. findFirst? → first element (ordered).
28. findAny? → any element.
29. forEach order? → Unspecified in parallel.
30. forEachOrdered? → Respects encounter order.
31. Collectors.toList vs toUnmodifiableList? → Mutable vs immutable.
32. groupingBy? → Map of grouped lists.
33. groupingBy downstream? → counting/mapping/summing.
34. partitioningBy? → Map<Boolean,List>.
35. counting? → Long count per group.
36. summingInt? → Integer sum.
37. averagingDouble? → Double average.
38. mapping collector? → Transform before collecting.
39. reducing collector? → Fold within collect.
40. minBy/maxBy? → Optional extreme.
41. toMap dup keys? → Throws unless merge fn.
42. toMap with supplier? → Choose map impl.
43. teeing? → Two collectors merged.
44. Stream.concat? → Combine two streams.
45. boxed()? → IntStream→Stream<Integer>.
46. mapToObj? → Primitive→object stream.
47. asLongStream? → Widen IntStream.
48. summaryStatistics? → count/sum/min/max/avg.
49. takeWhile? → Prefix while predicate (Java 9).
50. dropWhile? → Drop prefix (Java 9).
51. iterate with predicate? → Bounded iterate (Java 9).
52. ofNullable? → 0/1-element stream (Java 9).
53. Parallel stream source? → Common ForkJoinPool.
54. Set parallelism? → ForkJoinPool custom or system property.
55. Stateful op risk? → Breaks parallel correctness.
56. Side-effect risk? → Data races in parallel.
57. Is sorted stateful? → Yes (buffers).
58. Short-circuit ops? → limit, findFirst, anyMatch.
59. Lazy until? → Terminal op.
60. Reuse stream? → IllegalStateException.
61. Stream of map? → map.entrySet().stream().
62. Collect to TreeMap? → toMap(...,TreeMap::new).
63. Count by predicate? → filter().count().
64. First N? → limit(n).
65. Nth element? → skip(n-1).findFirst().
66. Flatten nested list? → flatMap(List::stream).
67. Unique by field? → collect(toMap(field, x->x,(a,b)->a)).values().
68. Sort by multiple keys? → comparing().thenComparing().
69. Reverse sort? → Comparator.reverseOrder().
70. Null-safe compare? → nullsFirst/nullsLast.
71. Map then sum? → mapToInt then sum.
72. Convert stream to array? → toArray(Type[]::new).
73. Lambda capture rule? → Effectively final variables.
74. Method ref types? → static/instance/arbitrary/constructor.
75. Function compose? → andThen / compose.
76. Predicate combine? → and/or/negate.
77. Consumer chain? → andThen.
78. Supplier use? → Lazy value / factory.
79. UnaryOperator? → Function<T,T>.
80. BinaryOperator? → BiFunction<T,T,T>.
81. Default method on functional iface? → Allowed.
82. Can lambda throw checked? → Only if SAM declares it.
83. this in lambda? → Enclosing instance.
84. this in anonymous class? → The anonymous instance.
85. Capturing vs non-capturing lambda? → Uses outer vars or not.
86. Collectors.joining args? → delimiter, prefix, suffix.
87. Stream to Optional reduce? → reduce(acc) returns Optional.
88. average returns? → OptionalDouble.
89. IntStream.sum empty? → 0.
90. max empty? → empty Optional.
91. Collect to string? → joining.
92. groupingByConcurrent? → Concurrent grouping.
93. toConcurrentMap? → Parallel-friendly map.
94. Why not forEach to build list? → Side effects; use collect.
95. Lazy infinite + limit safe? → Yes, short-circuits.
96. flatMap to IntStream? → flatMapToInt.
97. mapMulti (Java 16)? → 1→many without intermediate stream.
98. Stream debugging? → peek().
99. When loops over streams? → Hot paths/perf-critical or simple iteration.
100. Golden rule? → Keep lambdas pure, stateless, and non-interfering.
