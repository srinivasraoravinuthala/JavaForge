# JVM Internals

Runnable demos: [`pkg6jvm`](../../pkg6jvm). Deep notes below complement [Java Notes](../05-quick-ref/02-JavaNotes.md).

## 1. Execution pipeline
```
.java --javac--> .class (bytecode) --classloader--> JVM
   JVM = Interpreter + JIT (C1 client, C2 server) + Garbage Collector
```
- **Interpreter** runs bytecode immediately (fast startup).
- **JIT** compiles hot methods to native code (fast steady-state). Tiered compilation blends both.
- **HotSpot** profiles at runtime: inlining, escape analysis, loop unrolling, dead-code elimination.

## 2. Runtime data areas
| Area | Scope | Holds | Error |
|------|-------|-------|-------|
| Heap | shared | objects, arrays | `OutOfMemoryError: Java heap space` |
| Method area / **Metaspace** | shared | class metadata, static fields | `OutOfMemoryError: Metaspace` |
| JVM Stack | per thread | frames (locals, operands) | `StackOverflowError` |
| PC Register | per thread | current instruction | — |
| Native Stack | per thread | JNI calls | — |

**Heap layout (generational):** Young = Eden + Survivor S0/S1; Old (Tenured). New objects start in Eden; survivors are promoted to Old after surviving enough minor GCs.

## 3. Class loading
- **Phases:** Loading → Linking(Verify→Prepare→Resolve) → Initialization.
- **Loaders:** Bootstrap (core `java.*`, shown as `null`) → Platform → Application/System.
- **Delegation model:** a loader asks its parent first; only loads itself if the parent can't. Prevents core-class spoofing.
- **Initialization** runs static blocks/fields once, on first active use.

## 4. Garbage Collection
**Why:** automatic reclamation of unreachable objects (no manual `free`).
**Reachability:** an object is live if a **GC root** (stack locals, statics, JNI refs) can reach it.
**Phases (mark-sweep-compact):** mark live → sweep dead → compact to reduce fragmentation.
**Generational hypothesis:** most objects die young → collect Young region frequently (cheap minor GC), Old rarely (expensive major/full GC).

### Reference strength
Strong → Soft (memory-sensitive caches) → Weak (`WeakHashMap`) → Phantom (post-mortem cleanup via `Cleaner`). `finalize()` is deprecated.

### Collectors compared
| GC | Algorithm | Pause | Heap size | Use when |
|----|-----------|-------|-----------|----------|
| **Serial** (`-XX:+UseSerialGC`) | single-threaded mark-compact | high | small | tiny heaps, single core, CLI tools |
| **Parallel** (`-XX:+UseParallelGC`) | multi-threaded, throughput | medium-high | medium | batch jobs maximizing throughput |
| **G1** (`-XX:+UseG1GC`, default) | region-based, incremental, mostly concurrent | low-medium, targetable (`-XX:MaxGCPauseMillis`) | large | general server apps, balanced latency/throughput |
| **ZGC** (`-XX:+UseZGC`) | concurrent, colored pointers, load barriers | **< 1 ms**, pause-time independent of heap | up to multi-TB | low-latency services, very large heaps |
| **Shenandoah** (`-XX:+UseShenandoahGC`) | concurrent compaction (Brooks/load barriers) | **very low**, independent of heap | large | low-latency with moderate-large heaps |

**G1 detail:** divides heap into equal regions; collects regions with most garbage first ("garbage first"); concurrent marking + evacuation pauses; humongous objects span regions.
**ZGC detail:** nearly all work concurrent; uses colored pointers + load barriers; generational ZGC (Java 21) improves young-gen efficiency.
**Shenandoah detail:** concurrent evacuation/compaction so pause time doesn't grow with heap size; uses load-reference barriers.

### Choosing a GC
- Throughput batch → **Parallel**.
- Balanced default → **G1**.
- Strict low latency / huge heap → **ZGC** (or **Shenandoah**).

## 5. Common JVM flags
```
-Xms / -Xmx            initial / max heap
-Xss                   thread stack size
-XX:MetaspaceSize / -XX:MaxMetaspaceSize
-XX:+UseG1GC | -XX:+UseZGC | -XX:+UseShenandoahGC
-XX:MaxGCPauseMillis=200      (G1 target pause)
-verbose:gc / -Xlog:gc*       GC logging
-XX:+HeapDumpOnOutOfMemoryError
-XX:+PrintFlagsFinal          dump all flags
```

## 6. Troubleshooting tools
- `jps` (list JVMs), `jstack` (thread dump → deadlocks), `jmap`/`jcmd` (heap dump, metaspace),
  `jstat` (GC stats), `jconsole`/`VisualVM`/`JFR` (profiling), `jhsdb`.

## 7. Common interview pitfalls
- "Java has no memory leaks" → **false**: lingering references (static collections, listeners, ThreadLocals) leak.
- `System.gc()` forces GC → it's only a **hint**.
- Metaspace is on the heap → it's **native memory** (off-heap).
- Stack stores objects → no, **objects live on the heap**; the stack holds references/primitives.
