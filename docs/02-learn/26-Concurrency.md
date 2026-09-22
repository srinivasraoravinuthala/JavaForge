# 26 — Concurrency

**Previous:** [25 JVM & Memory](25-JVMAndMemory.md) · **Next:** [27 Design Patterns](27-DesignPatterns.md)

▶️ `pkg7concurrency/concurrency1ThreadBasics.java` → `concurrency8LocksAndCoordination.java`  
▶️ Advanced: `pkg16advconcurrency/`

---

## Core concepts

| Concept | Meaning |
|---------|---------|
| **Process** | Isolated program with own memory |
| **Thread** | Lightweight unit within a process; shares heap |
| **Race condition** | Outcome depends on timing without synchronization |
| **Deadlock** | Threads wait on each other forever |

---

## Building blocks

```java
// Thread
Thread t = Thread.startVirtualThread(() -> task());  // Java 21

// ExecutorService
ExecutorService pool = Executors.newFixedThreadPool(4);
Future<Integer> f = pool.submit(() -> compute());

// CompletableFuture
CompletableFuture.supplyAsync(() -> fetch())
    .thenApply(data -> transform(data))
    .thenAccept(System.out::println);

// Synchronization
synchronized (lock) { ... }
ReentrantLock lock = new ReentrantLock();
AtomicInteger counter = new AtomicInteger();
```

---

## Virtual threads (Java 21)

Millions of cheap threads for **blocking I/O**. Don't pool them like platform threads.

⚠️ **Pinning:** `synchronized` inside virtual thread may pin to carrier thread.

---

## Learning order

1. `concurrency1` Thread basics
2. `concurrency2` Runnable / Callable
3. `concurrency3` ExecutorService
4. `concurrency4` synchronized / volatile
5. `concurrency5` Concurrent collections
6. `concurrency6` CompletableFuture
7. `concurrency7` Virtual threads
8. `concurrency8` Locks

**Deep dive →** [04 Concurrency](../03-interview/04-Concurrency.md)

**Next →** [27 Design Patterns](27-DesignPatterns.md)
