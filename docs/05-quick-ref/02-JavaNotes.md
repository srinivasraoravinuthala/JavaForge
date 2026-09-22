# Java Notes — Concise Reference

## 1. JVM, JRE, JDK
- **JVM**: executes bytecode; provides memory management, GC, JIT.
- **JRE**: JVM + standard libraries (runtime).
- **JDK**: JRE + compiler (`javac`) + tools (jar, javadoc, jshell).
- Java is **compiled** (`.java`→`.class` bytecode) then **interpreted/JIT-compiled** by the JVM ("write once, run anywhere").

## 2. Memory model (runtime data areas)
- **Heap**: all objects + arrays; shared; GC-managed. Split into **Young** (Eden + 2 Survivor) and **Old/Tenured**.
- **Stack**: per-thread; stores frames (local vars, operand stack, return address). `StackOverflowError` on deep recursion.
- **Metaspace** (Java 8+, replaces PermGen): class metadata; native memory; grows dynamically.
- **PC Register**: per-thread current instruction pointer.
- **Native Method Stack**: for native (JNI) calls.

## 3. Garbage Collectors
| GC | Best for | Notes |
|----|----------|-------|
| **Serial** | small heaps, single CPU | stop-the-world, simple |
| **Parallel (Throughput)** | batch jobs | maximizes throughput, longer pauses |
| **G1** (default since 9) | balanced, large heaps | region-based, predictable pauses |
| **ZGC** | ultra-low latency, huge heaps (TB) | concurrent, pauses < 1ms, colored pointers |
| **Shenandoah** | low latency | concurrent compaction, Brooks pointers |

Enable: `-XX:+UseG1GC`, `-XX:+UseZGC`, `-XX:+UseShenandoahGC`.

**GC concepts:** roots, reachability, mark-sweep-compact, generational hypothesis (most objects die young), minor vs major (full) GC, stop-the-world pauses.

## 4. Class loading
Phases: **Loading → Linking (Verify → Prepare → Resolve) → Initialization**.
- **Loaders (delegation model):** Bootstrap → Platform (ext) → Application (system). Child delegates to parent first.
- Static blocks/fields initialized at init time, top-to-bottom, on first active use.

## 5. `==` vs `.equals()`
- `==` compares references (or primitive values).
- `.equals()` compares logical equality (override with `hashCode`).
- **Contract:** equal objects must have equal hash codes.
- Integer cache: `-128..127` are cached (`Integer.valueOf`).

## 6. String facts
- Immutable; stored in **string pool** (interned literals).
- `new String("x")` creates a new heap object (not pooled unless `.intern()`).
- Use `StringBuilder` for loops (not `+`).

## 7. Collections decisions
- Need order of insertion → `ArrayList`, `LinkedHashMap/Set`.
- Need sorting → `TreeMap/TreeSet` (Red-Black tree, O(log n)).
- Need uniqueness → `Set`.
- Need FIFO → `ArrayDeque`/`LinkedList`; priority → `PriorityQueue`.
- Need thread-safety → `ConcurrentHashMap`, `CopyOnWriteArrayList`, `BlockingQueue`.
- `HashMap` since Java 8: buckets convert to red-black trees when > 8 entries collide.

## 8. Generics
- Compile-time type safety; **type erasure** at runtime (no generic type info).
- PECS: **Producer Extends, Consumer Super** (`? extends T` to read, `? super T` to write).
- Can't create `new T[]`, can't have generic arrays of parameterized types, no primitives.

## 9. Functional & Streams
- Lambdas implement **functional interfaces** (one abstract method).
- Streams are **lazy**: intermediate ops (map/filter) build a pipeline; terminal ops (collect/forEach/reduce) trigger it.
- Streams don't mutate the source; prefer stateless, non-interfering functions.
- Parallel streams use the common ForkJoinPool — measure before using.

## 10. Concurrency essentials
- **happens-before**: guarantees visibility & ordering between actions.
- `volatile`: visibility (not atomicity for compound ops).
- `synchronized`: mutual exclusion + visibility (monitor lock).
- Atomics (`AtomicInteger`) use CAS (lock-free).
- Prefer `ExecutorService` over raw threads; `CompletableFuture` for async pipelines.
- **Virtual threads (Java 21)**: lightweight, JVM-scheduled, great for high-concurrency blocking I/O.
- Hazards: race conditions, deadlock, livelock, starvation, visibility bugs.

## 11. equals/hashCode/Comparable
```java
@Override public boolean equals(Object o){...}
@Override public int hashCode(){ return Objects.hash(a,b); }
class X implements Comparable<X>{ public int compareTo(X o){...} }
Comparator.comparing(X::getA).thenComparing(X::getB).reversed();
```

## 12. Common pitfalls
- Autoboxing in tight loops (perf).
- Mutating a collection while iterating → `ConcurrentModificationException` (use `Iterator.remove` or streams).
- Floating point equality; use `BigDecimal` for money.
- Catching `Exception`/`Throwable` too broadly.
- Forgetting to close resources (use try-with-resources).
