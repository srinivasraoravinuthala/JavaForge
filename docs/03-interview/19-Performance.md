# Performance & Tuning — Interview Questions (60+)

## Detailed Questions

### 1. How do you approach performance problems?
- **Short:** Measure first, find the bottleneck, fix, re-measure.
- **Detailed:** Avoid premature optimization. Establish a baseline and a target, profile (CPU, allocation, GC, locks), fix the dominant bottleneck, and verify with benchmarks. Optimize algorithms before micro-tuning.
- **Example:** Profiling reveals an O(n²) loop—fix the algorithm, not the syntax.

### 2. How do you benchmark Java correctly?
- **Short:** Use JMH; account for JIT warmup.
- **Detailed:** Naive `System.nanoTime()` loops are misleading due to JIT warmup, dead-code elimination, and GC. JMH handles warmup iterations, forks, and blackholes to prevent the optimizer from removing your code.
- **Example:** `@Benchmark` methods with `Blackhole`.

### 3. Common Java performance pitfalls?
- **Short:** Autoboxing, string concat in loops, wrong data structures, excessive allocation.
- **Detailed:** Boxing in tight loops, `+` concatenation in loops (use StringBuilder), `LinkedList` where `ArrayList` fits, unbounded caches, logging in hot paths, and synchronized when atomics suffice.
- **Example:** Replace `Integer` accumulation with `int`/`IntStream`.

### 4. How does GC affect performance and how to tune it?
- **Short:** Pauses + allocation rate; tune GC choice and heap.
- **Detailed:** High allocation → frequent GC. Reduce allocations, reuse buffers, right-size the heap, and pick a GC matching the goal (Parallel for throughput, G1 default, ZGC/Shenandoah for latency). Measure with GC logs before tuning.
- **Example:** Latency-sensitive service → ZGC + adequate heap.

### 5. How to find and fix a memory leak?
- **Short:** Heap dump → analyze retained sets → remove lingering refs.
- **Detailed:** Capture with `-XX:+HeapDumpOnOutOfMemoryError`/`jcmd`, analyze with MAT/VisualVM, look for growing static collections, caches, ThreadLocals, and listeners. Fix by bounding caches, weak refs, and proper cleanup.
- **Example:** A static `Map` used as a cache without eviction.

### 6. CPU-bound vs I/O-bound tuning?
- **Short:** CPU: ~#cores threads, better algorithms; I/O: more concurrency/async.
- **Detailed:** CPU-bound work saturates cores—reduce work, parallelize with ForkJoin, size pools near core count. I/O-bound work waits—use async/non-blocking or virtual threads and higher concurrency.
- **Example:** Many DB calls → virtual threads; matrix math → ForkJoin.

---

## Rapid-Fire (Q → A)

1. First rule of optimization? → Measure.
2. Premature optimization? → Root of much evil.
3. Profiler examples? → JFR, async-profiler, VisualVM.
4. Benchmark tool? → JMH.
5. Why not nanoTime loops? → JIT/GC distortion.
6. Warmup matters because? → JIT compiles hot code.
7. Dead-code elimination? → Optimizer removes unused results.
8. Blackhole? → Prevents DCE in JMH.
9. Big-O first? → Algorithmic complexity dominates.
10. String concat in loop? → Use StringBuilder.
11. Autoboxing cost? → Allocation/unbox.
12. Primitive streams? → Avoid boxing.
13. ArrayList vs LinkedList perf? → ArrayList usually faster.
14. HashMap sizing? → Pre-size to avoid rehash.
15. Object allocation cost? → Pressure on GC.
16. Object pooling? → Only for expensive objects.
17. Escape analysis? → May stack-allocate.
18. Lock contention symptom? → Threads blocked.
19. Reduce contention? → Finer locks/atomics/striping.
20. LongAdder vs AtomicLong? → Better under contention.
21. False sharing fix? → Padding/@Contended.
22. volatile cost? → Cheap read, ordered write.
23. synchronized cost? → Uncontended cheap, contended expensive.
24. ThreadLocal perf use? → Avoid shared contention.
25. GC log flag? → -Xlog:gc*.
26. High allocation rate? → More GC pauses.
27. Reduce allocations? → Reuse, primitives, streams care.
28. Heap too small? → Frequent GC/OOM.
29. Heap too large? → Long pauses (older GCs).
30. Set Xms=Xmx? → Avoid resize pauses.
31. Choose Parallel GC? → Throughput batch.
32. Choose G1? → Balanced default.
33. Choose ZGC? → Low latency/large heap.
34. Compressed oops? → Heap < 32GB.
35. Off-heap memory? → Direct ByteBuffers.
36. Memory-mapped files? → Fast large file I/O.
37. Buffered I/O? → Reduce syscalls.
38. NIO benefit? → Non-blocking/selectors.
39. Connection pooling? → Reuse DB connections.
40. N+1 query problem? → Batch/join instead.
41. Index missing symptom? → Full table scans.
42. Caching benefit? → Avoid repeated work.
43. Cache hit ratio? → Effectiveness metric.
44. Lazy loading? → Defer expensive work.
45. Batch processing? → Amortize overhead.
46. Vectorization? → SIMD via JIT.
47. Inlining? → Removes call overhead.
48. Loop unrolling? → Fewer branches.
49. Branch misprediction? → Pipeline stalls.
50. Cache locality? → Sequential access faster.
51. Array vs linked traversal? → Array better locality.
52. StringBuilder capacity? → Preset to avoid growth.
53. Regex precompile? → Reuse Pattern.
54. Logging in hot path? → Guard/parameterize.
55. Reflection cost? → Cache MethodHandles.
56. Serialization cost? → Prefer efficient formats.
57. JSON parsing cost? → Stream/large payloads.
58. Thread pool sizing CPU-bound? → ~#cores.
59. Thread pool sizing I/O-bound? → Higher/virtual threads.
60. Little's Law? → L = λ × W (concurrency = arrival × latency).
61. Throughput vs latency trade-off? → Batch increases both differently.
62. Tail latency (p99)? → Worst-case user experience.
63. Reduce p99? → Timeouts, hedging, isolation.
64. Golden rule? → Profile, fix the biggest bottleneck, verify, repeat.
