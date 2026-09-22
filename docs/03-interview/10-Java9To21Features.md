# Java 9–21 Features — Interview Questions (95+)

See [`pkg2versions`](../../pkg2versions) and `core16`–`core17`, `core24`–`core28`.

---

## Detailed Questions

### 1. Java release cadence since Java 9?
- **Short:** 6-month releases; LTS every 2 years (11, 17, 21).
- **Detailed:** Feature releases (9,10,12…) get updates for ~6 months; LTS gets long-term support. Production typically targets LTS. Preview/incubator features need `--enable-preview`.
- **Example:** This project targets Java 21 LTS.

### 2. Java 9 — modules (JPMS)?
- **Short:** `module-info.java` defines exports/requires; strong encapsulation.
- **Detailed:** Modules control visibility at compile and runtime. `requires transitive` passes dependency to consumers. `opens` for reflection (Hibernate). Classpath JARs become unnamed module.
- **Example:** [`pkg15modules`](../../pkg15modules).

### 3. Java 9 — collection factories?
- **Short:** `List.of`, `Set.of`, `Map.of` — immutable, null-hostile.
- **Detailed:** Compact, thread-safe, no setters. `Map.of` max 10 pairs; use `Map.ofEntries` beyond. Duplicate keys throw `IllegalArgumentException`.
- **Example:** `List.of(1,2,3)` vs `Arrays.asList` (mutable size).

### 4. Java 9 — Stream takeWhile/dropWhile?
- **Short:** Short-circuit on sorted/predicate prefix; not same as filter.
- **Detailed:** `takeWhile` stops at first false; on infinite stream behaves like limit while true. `dropWhile` skips while true, then takes rest.
- **Example:** `Stream.of(1,2,3,4,1).takeWhile(x->x<4)` → [1,2,3].

### 5. Java 10 — `var`?
- **Short:** Local variable type inference; still statically typed.
- **Detailed:** Compiler infers type from initializer. Not for fields, method params, or without initializer. Use when type obvious (`var list = new ArrayList<String>()`). Avoid obscuring important types.
- **Example:** `var map = Map.of("a", 1);`

### 6. Java 11 — HttpClient?
- **Short:** Modern async/sync HTTP in `java.net.http`; replaces Apache for many cases.
- **Detailed:** HTTP/2, WebSocket, CompletableFuture async API. Immutable request/response objects.
- **Example:** [`pkg10networking/networking5HttpClient.java`](../../pkg10networking/networking5HttpClient.java).

### 7. Java 14 — switch expressions?
- **Short:** Switch as expression with `->` and `yield`; exhaustiveness for enums.
- **Detailed:** No fall-through with arrows. Blocks use `yield value`. Compiler checks enum/sealed coverage when exhaustive.
- **Example:** `String r = switch (d) { case MON -> "weekday"; default -> "other"; };`

### 8. Java 15 — text blocks?
- **Short:** Multi-line string literals with `"""`; auto-indent strip.
- **Detailed:** Escape sequences still work; concatenation with `+` allowed. `.formatted()` for interpolation-style formatting.
- **Example:** JSON/SQL in `core9StringsDemo`.

### 9. Java 16 — records?
- **Short:** Immutable data carriers: canonical ctor, equals/hashCode/toString, accessors.
- **Detailed:** Compiler generates boilerplate. Can implement interfaces, define compact ctor validation. Not JPA entities without care (no no-arg ctor by default).
- **Example:** `record Point(int x, int y) {}`

### 10. Java 16 — pattern matching for instanceof?
- **Short:** `if (o instanceof String s)` binds variable in scope.
- **Detailed:** Eliminates cast after instanceof. Works with null (false, no binding).
- **Example:** `if (obj instanceof Integer n) sum += n;`

### 11. Java 17 — sealed classes?
- **Short:** Restrict which classes can extend/implement; enables exhaustive switches.
- **Detailed:** `sealed class X permits A, B`. Subclasses must be `final`, `sealed`, or `non-sealed`. Works with pattern matching switch.
- **Example:** `core17SealedClassesDemo`.

### 12. Java 21 — virtual threads?
- **Short:** Lightweight threads for massive blocking I/O concurrency.
- **Detailed:** JVM-scheduled; cheap to create millions. Don't pool them like platform threads. Pinning issue: synchronized block may pin carrier thread.
- **Example:** `Thread.startVirtualThread(() -> ...)` or `Executors.newVirtualThreadPerTaskExecutor()`.

### 13. Java 21 — record patterns?
- **Short:** Deconstruct records in switch/instanceof patterns.
- **Detailed:** `case Point(int x, int y)` extracts components. Guards with `when`. Null case explicit in switch.
- **Example:** `versions6Java21Features.describe()`.

### 14. Java 21 — sequenced collections?
- **Short:** `SequencedCollection`, `SequencedSet`, `SequencedMap` — uniform first/last/reversed.
- **Detailed:** `getFirst()`, `getLast()`, `reversed()`. Implemented by `LinkedHashMap`, `ArrayList`, etc.
- **Example:** `list.getFirst()` instead of `list.get(0)` with clearer intent.

### 15. Preview features — how to use?
- **Short:** `--enable-preview` on compile and run; API may change.
- **Detailed:** String templates, structured concurrency were previews. Don't use preview in production without acceptance of churn.
- **Example:** `java --enable-preview MyApp.java`

---

## Rapid-Fire (Q → A)

1. Java 8 headline? → Lambdas, streams, java.time.
2. Java 9 headline? → Modules, JShell, factory methods.
3. Java 10 headline? → var.
4. Java 11 LTS headline? → HttpClient, String methods, run single-file.
5. Java 14 headline? → Records preview, switch expr, helpful NPEs.
6. Java 15 headline? → Text blocks, sealed classes preview.
7. Java 16 headline? → Records, instanceof patterns.
8. Java 17 LTS headline? → Sealed classes, pattern switch preview.
9. Java 21 LTS headline? → Virtual threads, record patterns, sequenced collections.
10. LTS releases? → 11, 17, 21 (also 8 before cadence change).
11. module-info exports? → Public API of module.
12. requires transitive? → Implicit dependency for consumers.
13. opens package? → Deep reflection for framework.
14. unnamed module? → Classpath JARs on module path.
15. List.of mutable? → No.
16. List.of null element? → NPE.
17. Map.of max pairs? → 10.
18. copyOf collections? → Immutable copy.
19. var for field? → Not allowed.
20. var without init? → Not allowed.
21. HttpClient in which module? → java.net.http.
22. run-java source? → java File.java (11+).
23. switch arrow no fall-through? → Correct.
24. switch yield? → Return from block case.
25. record can extend class? → No (implicit extends Record).
26. record can implement interface? → Yes.
27. record accessor name? → `name()` not `getName()` unless override.
28. compact record ctor? → Validates before field assign.
29. sealed permits required? → Yes on sealed type.
30. non-sealed subclass? → Open to further extension.
31. pattern switch exhaustiveness? → Compiler checks sealed/enums.
32. virtual thread carrier? → Platform thread pool underneath.
33. pin virtual thread? → synchronized/native on carrier.
34. use platform threads when? → CPU-bound, many cores, short tasks.
35. structured concurrency preview? → Scope for child task lifetime.
36. String templates preview? → STR."Hello \{name}".
37. foreign function API? → Panama; call native code.
38. vector API incubating? → SIMD operations.
39. ZGC generational? → Java 21 improvement.
40. Shenandoah? → Low-pause GC alternative.
41. deprecate finalize? → Yes; use Cleaner.
42. strong encapsulation JDK internals? → Illegal access warnings/errors.
43. jlink? → Custom runtime image.
44. reactive streams in JDK? → Flow API (Java 9).
45. Optional.orElseThrow? → Java 10 (was get()).
46. Collectors.toUnmodifiableList? → Java 10.
47. teeing collector? → Combine two collectors.
48. takeWhile on unordered stream? → Still short-circuits first false.
49. dropWhile difference from skip? → Predicate-based prefix skip.
50. Predicate.not? → Java 11 negation.
51. Files.readString? → Java 11.
52. isIdentical for strings? → Reference equality helper.
53. record serialization? → Serial form defined; consider stability.
54. record reflection? → Record components API.
55. hidden classes? → Framework-generated (lambda impls).
56. nestmates? → Inner/outer access control.
57. compact source? → Smaller source file feature (preview).
58. multi-file source? → Single class per file loosened (preview).
59. deprecate SecurityManager? → Removed/disabled modern JDKs.
60. javax to jakarta? → EE namespace change (not JDK but ecosystem).
61. microprofile? → Small EE specs on Jakarta.
62. enable-preview compile? → javac --enable-preview --release 21.
63. migration 8→11 pain? → JAXB removed, illegal reflective access.
64. migration 11→17? → Fewer breaking; sealed/records adoption.
65. migration 17→21? → Virtual threads opt-in.
66. jpackage? → Native installer (14+).
67. instanceof pattern null? → false, no binding.
68. switch null case? → Java 21 explicit `case null`.
69. sequenced map reversed? → Reverse-order view.
70. LinkedHashMap getFirst? → Java 21 SequencedMap.
71. ArrayList reversed view? → SequencedCollection.
72. ListIterator vs reversed? → reversed() is view.
73. record generic? → `record Box<T>(T value) {}`.
74. local record? → Allowed in methods.
75. anonymous to private instance? → Less boilerplate with records.
76. pattern for guarded case? → `case Integer i when i > 0`.
77. exhaustiveness default needed? → For non-sealed/non-enum.
78. java.time in which version? → Java 8.
79. var with lambda? → Must infer functional interface type.
80. var with diamond? → `var list = new ArrayList<>()` infers raw-ish; specify generic.
