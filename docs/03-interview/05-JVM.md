# JVM Internals & GC — Interview Questions (120+)

## Detailed Questions

### 1. What are the JVM runtime data areas?
- **Short:** Heap, Stacks, Metaspace, PC registers, native stacks.
- **Detailed:** **Heap** (shared, objects/arrays, GC-managed), **JVM Stack** (per-thread frames), **Metaspace** (class metadata in native memory), **PC register** (per-thread instruction pointer), **native method stack** (JNI).
- **Example:** Local `int` lives on the stack; `new Object()` lives on the heap.

### 2. Stack vs heap?
- **Short:** Stack = per-thread frames (locals); heap = shared objects.
- **Detailed:** Stack stores method frames, primitive locals, and references; it's fast and auto-freed on return (LIFO). Heap holds all objects, is shared across threads, and is reclaimed by GC.
- **Example:** Deep recursion → StackOverflowError; too many objects → OutOfMemoryError (heap).

### 3. What replaced PermGen and why?
- **Short:** Metaspace (Java 8); PermGen had fixed size and caused OOM.
- **Detailed:** Class metadata moved from heap PermGen to native-memory Metaspace, which grows dynamically (bounded by `-XX:MaxMetaspaceSize`), reducing `OutOfMemoryError: PermGen`.
- **Example:** Many dynamically generated classes used to exhaust PermGen.

### 4. Explain the class loading process.
- **Short:** Loading → Linking (Verify/Prepare/Resolve) → Initialization.
- **Detailed:** Load reads bytecode; Verify checks safety; Prepare allocates static fields with defaults; Resolve turns symbolic refs into direct; Initialize runs static initializers (once, on first active use).
- **Example:** Accessing a static field triggers class init.

### 5. Describe the class loader delegation model.
- **Short:** Parent-first: Bootstrap → Platform → Application.
- **Detailed:** Each loader asks its parent before loading itself, preventing core classes from being overridden by user code (security). Bootstrap loads `java.*` (shown as null).
- **Example:** `String.class.getClassLoader()` is null (bootstrap).

### 6. How does garbage collection decide what to collect?
- **Short:** Reachability from GC roots.
- **Detailed:** Objects reachable from roots (stack locals, statics, JNI refs, active threads) are live; everything else is garbage. GC marks live, then sweeps/compacts dead.
- **Example:** Setting the only reference to null makes an object eligible.

### 7. What is the generational hypothesis?
- **Short:** Most objects die young.
- **Detailed:** Heap splits into Young (Eden + Survivors) and Old. Minor GC collects Young frequently and cheaply; survivors are promoted to Old, collected rarely by expensive major/full GC.
- **Example:** Short-lived request objects die in Eden.

### 8. Compare G1, ZGC, and Shenandoah.
- **Short:** G1 = balanced default; ZGC/Shenandoah = ultra-low pause.
- **Detailed:** **G1** is region-based with targetable pauses (`MaxGCPauseMillis`), good general default. **ZGC** does almost everything concurrently using colored pointers/load barriers, sub-millisecond pauses independent of heap size (to multi-TB). **Shenandoah** does concurrent compaction so pauses don't grow with heap.
- **Example:** Low-latency trading service → ZGC; batch ETL → Parallel.

### 9. What is a stop-the-world pause?
- **Short:** GC phase where app threads are paused.
- **Detailed:** Some GC work needs a consistent snapshot; STW pauses all application threads. Modern collectors minimize STW by doing marking/compaction concurrently.
- **Example:** Long Full GC pause causes latency spikes.

### 10. What are the reference types?
- **Short:** Strong, Soft, Weak, Phantom.
- **Detailed:** Strong = never collected while reachable. Soft = collected under memory pressure (caches). Weak = collected at next GC if only weakly reachable (`WeakHashMap`). Phantom = enqueued after collection for cleanup (`Cleaner`).
- **Example:** Cache values via SoftReference; metadata via WeakHashMap.

### 11. Does System.gc() force GC?
- **Short:** No, it's only a hint.
- **Detailed:** The JVM may ignore it. Relying on it is an anti-pattern; can be disabled with `-XX:+DisableExplicitGC`.
- **Example:** Tests sometimes call it but it's not guaranteed.

### 12. What is JIT compilation?
- **Short:** Runtime compilation of hot bytecode to native code.
- **Detailed:** HotSpot interprets first, profiles, then JIT-compiles hot methods (C1 client, C2 server; tiered). Optimizations: inlining, escape analysis, loop unrolling, dead-code elimination.
- **Example:** A loop runs faster after it becomes "hot".

### 13. What is escape analysis?
- **Short:** Determines if an object escapes a method; may allocate on stack/eliminate.
- **Detailed:** If an object never escapes, the JIT can scalar-replace it (no heap allocation) and remove synchronization (lock elision).
- **Example:** A short-lived local object may avoid heap allocation.

### 14. How do you diagnose a memory leak?
- **Short:** Heap dump + analyze dominators/retained sizes.
- **Detailed:** Use `-XX:+HeapDumpOnOutOfMemoryError`, `jmap`/`jcmd` for dumps, and Eclipse MAT/VisualVM to find growing retained sets (often static collections, caches, ThreadLocals, listeners).
- **Example:** A static `List` that only grows.

### 15. Common JVM tuning flags?
- **Short:** -Xms/-Xmx, -Xss, GC selection, logging.
- **Detailed:** `-Xms/-Xmx` (heap), `-Xss` (stack), `-XX:+UseG1GC/ZGC/ShenandoahGC`, `-XX:MaxGCPauseMillis`, `-Xlog:gc*`, `-XX:+HeapDumpOnOutOfMemoryError`.
- **Example:** `java -Xms2g -Xmx2g -XX:+UseZGC App`.

---

## Rapid-Fire (Q → A)

1. Bytecode file extension? → .class.
2. Who executes bytecode? → JVM.
3. JIT stands for? → Just-In-Time compiler.
4. AOT in Java? → Ahead-of-time (jaotc/GraalVM native image).
5. Interpreter role? → Run bytecode immediately.
6. C1 vs C2? → Client (fast compile) vs server (deep opt).
7. Tiered compilation? → Mix interpreter + C1 + C2.
8. Where do objects live? → Heap.
9. Where do locals live? → Stack frame.
10. Where do statics live? → Metaspace (with class).
11. Where do string literals live? → String pool (heap).
12. Young gen parts? → Eden + 2 Survivors.
13. Old gen aka? → Tenured.
14. Minor GC collects? → Young gen.
15. Major/Full GC collects? → Old/whole heap.
16. Promotion? → Survivor → Old after threshold.
17. TLAB? → Thread-Local Allocation Buffer.
18. Default GC (Java 9+)? → G1.
19. Throughput GC? → Parallel.
20. Lowest latency GC? → ZGC/Shenandoah.
21. Serial GC use? → Small heaps/single core.
22. G1 region size? → Power-of-two, 1–32MB.
23. Humongous object? → Spans ≥ half a region.
24. ZGC pointer trick? → Colored pointers.
25. ZGC barrier? → Load barrier.
26. Shenandoah barrier? → Load-reference barrier.
27. STW means? → Stop-the-world.
28. GC roots examples? → Stack locals, statics, JNI, threads.
29. Reachability? → Path from a root.
30. Finalize status? → Deprecated.
31. Cleaner? → Modern post-mortem cleanup.
32. PhantomReference use? → Cleanup notification.
33. WeakHashMap use? → Auto-evicting cache by key.
34. SoftReference use? → Memory-sensitive cache.
35. OutOfMemoryError types? → Heap, Metaspace, GC overhead, direct buffer.
36. GC overhead limit? → Too much time in GC, little reclaimed.
37. StackOverflowError cause? → Deep recursion.
38. -Xss controls? → Thread stack size.
39. -Xmx controls? → Max heap.
40. -Xms controls? → Initial heap.
41. Set them equal? → Avoids resizing pauses.
42. MetaspaceSize flag? → -XX:MaxMetaspaceSize.
43. GC logging flag? → -Xlog:gc*.
44. Heap dump on OOM? → -XX:+HeapDumpOnOutOfMemoryError.
45. Thread dump tool? → jstack.
46. Heap dump tool? → jmap/jcmd.
47. GC stats tool? → jstat.
48. Profiler? → JFR/VisualVM/async-profiler.
49. jps? → List JVM processes.
50. jcmd? → Diagnostic commands.
51. JFR? → Java Flight Recorder.
52. Class init trigger? → First active use.
53. Static block runs? → Once at init.
54. Lazy class loading? → Loaded when needed.
55. Bootstrap loader loads? → java.* core.
56. Platform loader loads? → JDK modules.
57. App loader loads? → Classpath classes.
58. Custom class loader use? → Plugins, hot reload.
59. Parent-first benefit? → Security/consistency.
60. ClassNotFoundException? → Missing at runtime lookup.
61. NoClassDefFoundError? → Present at compile, missing at runtime.
62. UnsatisfiedLinkError? → Missing native lib.
63. Verify phase? → Bytecode safety check.
64. Prepare phase? → Static defaults allocated.
65. Resolve phase? → Symbolic → direct refs.
66. Method area now? → Metaspace.
67. Constant pool? → Per-class symbol table.
68. String dedup? → G1 feature to share char arrays.
69. Compressed oops? → 32-bit refs on 64-bit heaps < 32GB.
70. Why heap < 32GB matters? → Keeps compressed oops.
71. Object header size? → ~12–16 bytes.
72. Object alignment? → 8-byte boundaries.
73. Escape analysis benefit? → Stack allocation/lock elision.
74. Scalar replacement? → Replace object with its fields.
75. Lock elision? → Remove unneeded sync.
76. Inlining? → Replace call with body.
77. Deoptimization? → Revert JIT assumptions.
78. Safepoint? → Where threads can pause for GC.
79. Card table? → Tracks old→young refs.
80. Remembered set? → Region cross-references (G1).
81. Write barrier? → Records reference writes.
82. Concurrent marking? → Mark live without full STW.
83. Mixed GC (G1)? → Young + some old regions.
84. Evacuation? → Copy live objects to new region.
85. Fragmentation fix? → Compaction.
86. Direct memory? → Off-heap (NIO ByteBuffer).
87. -XX:MaxDirectMemorySize? → Caps direct buffers.
88. GC tuning first step? → Measure with logs.
89. Latency vs throughput? → Pause time vs work done.
90. Allocation rate impact? → Higher → more GC.
91. Large heap GC choice? → ZGC/Shenandoah.
92. Batch job GC choice? → Parallel.
93. Default pause target G1? → 200ms.
94. Survivor ratio? → Eden:Survivor sizing.
95. Tenuring threshold? → Age to promote.
96. Premature promotion? → Survivors too small.
97. Memory leak signs? → Growing old gen, frequent full GC.
98. ThreadLocal leak fix? → remove() after use.
99. Static cache leak fix? → Bounded cache/weak refs.
100. Native memory leak? → Direct buffers/JNI.
101. -verbose:class? → Log class loading.
102. -XX:+PrintFlagsFinal? → Dump all flags.
103. GraalVM benefit? → Native images, polyglot.
104. Native image trade-off? → Fast startup, limited reflection.
105. CDS? → Class Data Sharing (faster startup).
106. AppCDS? → App-level CDS.
107. JIT vs AOT? → Runtime opt vs precompiled.
108. Why interpret first? → Fast startup before profiling.
109. Hot method? → Frequently executed → JIT'd.
110. Tier 4? → C2 fully optimized.
111. Bytecode verification importance? → Security/safety.
112. Reflection cost? → Slower, bypasses checks.
113. MethodHandle? → Faster reflective invocation.
114. invokedynamic use? → Lambdas, string concat.
115. String concat (Java 9+)? → invokedynamic-based.
116. Metaspace OOM cause? → Too many classes/classloaders.
117. Classloader leak? → Retained loader keeps classes.
118. PhantomReference vs finalize? → Deterministic cleanup queue.
119. GC ergonomics? → JVM auto-tunes defaults.
120. First GC tuning rule? → Don't tune prematurely; measure.
