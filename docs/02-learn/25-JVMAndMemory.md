# 25 — JVM & Memory

**Previous:** [24 LeetCode](24-LeetCode.md) · **Next:** [26 Concurrency](26-Concurrency.md)

▶️ `pkg6jvm/jvm1ClassLoadingDemo.java` → `jvm3GarbageCollectionDemo.java`

---

## JDK vs JRE vs JVM

| Component | Role |
|-----------|------|
| **JVM** | Runs bytecode, manages memory, JIT compiles hot code |
| **JRE** | JVM + standard libraries (runtime) |
| **JDK** | JRE + `javac` + tools |

---

## Memory areas

| Area | Stores | Notes |
|------|--------|-------|
| **Heap** | Objects, arrays | GC-managed; Young + Old generations |
| **Stack** | Method frames, locals | Per thread; `StackOverflowError` |
| **Metaspace** | Class metadata | Replaced PermGen in Java 8 |

---

## Garbage collectors

| GC | Best for |
|----|----------|
| **G1** (default) | Balanced, large heaps |
| **ZGC** | Ultra-low latency |
| **Parallel** | Throughput / batch jobs |

Enable: `-XX:+UseG1GC`, `-XX:+UseZGC`

---

## Class loading

```
Loading → Linking (Verify, Prepare, Resolve) → Initialization
```

Delegation: Bootstrap → Platform → Application classloader.

**Deep dive →** [04-reference/02-JVMInternals.md](../04-reference/02-JVMInternals.md) · [03-interview/05-JVM.md](../03-interview/05-JVM.md) · [05-quick-ref/02-JavaNotes.md](../05-quick-ref/02-JavaNotes.md)

**Next →** [26 Concurrency](26-Concurrency.md)
