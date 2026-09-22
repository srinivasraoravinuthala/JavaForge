# Concurrency & Multithreading — Interview Questions (150+)

## Detailed Questions

### 1. Process vs thread?
- **Short:** Processes have isolated memory; threads share the process heap.
- **Detailed:** Threads are lightweight, share heap/metaspace but have their own stack and PC. Communication between threads is via shared memory (needs synchronization); processes use IPC.
- **Example:** A web server uses many threads in one JVM process.

### 2. How do you create threads in Java?
- **Short:** Implement Runnable/Callable, or use an Executor; Java 21 adds virtual threads.
- **Detailed:** Prefer `Runnable`/`Callable` submitted to an `ExecutorService` over subclassing `Thread`. `Thread.ofVirtual()` creates virtual threads.
- **Example:** `Executors.newFixedThreadPool(4).submit(task);`

### 3. start() vs run()?
- **Short:** start() spawns a new thread; run() executes on the current thread.
- **Detailed:** Calling `run()` directly is just a normal method call—no concurrency. `start()` schedules the thread and the JVM calls `run()` on the new thread.
- **Example:** `new Thread(task).start();`

### 4. What is a race condition?
- **Short:** Result depends on unsynchronized timing of threads.
- **Detailed:** Two threads access shared mutable state and at least one writes, with no happens-before ordering. `count++` (read-modify-write) is the classic example.
- **Example:** Two threads incrementing a shared int lose updates.

### 5. synchronized vs volatile?
- **Short:** synchronized = mutual exclusion + visibility; volatile = visibility only.
- **Detailed:** `synchronized` provides atomicity for the block and establishes happens-before via the monitor. `volatile` guarantees reads see the latest write but does NOT make compound actions (`x++`) atomic.
- **Example:** Use volatile for a `boolean running` flag; synchronized/atomic for counters.

### 6. What is the Java Memory Model and happens-before?
- **Short:** Rules guaranteeing visibility/ordering across threads.
- **Detailed:** Happens-before relations (program order, monitor lock/unlock, volatile write/read, thread start/join) ensure one action's effects are visible to another. Without them, reordering and stale reads are legal.
- **Example:** Unlocking a monitor happens-before another thread locking it.

### 7. What is a deadlock and how to avoid it?
- **Short:** Threads wait forever on each other's locks; avoid with lock ordering.
- **Detailed:** Four Coffman conditions: mutual exclusion, hold-and-wait, no preemption, circular wait. Avoid by consistent global lock ordering, tryLock with timeout, or lock-free structures.
- **Example:** T1 holds A waits B; T2 holds B waits A.

### 8. ExecutorService benefits over raw threads?
- **Short:** Pooling, lifecycle, task submission, results.
- **Detailed:** Thread creation is expensive; pools reuse threads, bound concurrency, queue tasks, and return `Future`s. Lifecycle via `shutdown`/`awaitTermination`.
- **Example:** `var pool = Executors.newFixedThreadPool(8);`

### 9. Callable vs Runnable?
- **Short:** Callable returns a value and can throw checked exceptions.
- **Detailed:** `Runnable.run()` returns void; `Callable.call()` returns a result wrapped in a `Future`.
- **Example:** `Future<Integer> f = pool.submit(() -> 42);`

### 10. What is CompletableFuture?
- **Short:** Composable, non-blocking async computations.
- **Detailed:** Build pipelines with `thenApply/thenCompose/thenCombine`, handle errors with `exceptionally/handle`, coordinate with `allOf/anyOf`. Runs on ForkJoinPool by default or a supplied executor.
- **Example:** `supplyAsync(this::load).thenApply(this::parse);`

### 11. What are virtual threads (Java 21)?
- **Short:** Lightweight JVM-scheduled threads for massive concurrency.
- **Detailed:** Virtual threads mount onto carrier (platform) threads; blocking a virtual thread unmounts it, so millions can exist cheaply. Ideal for blocking I/O; not for CPU-bound work. Avoid `synchronized` around blocking calls (pinning)—use `ReentrantLock`.
- **Example:** `Executors.newVirtualThreadPerTaskExecutor()`.

### 12. Atomic classes and CAS?
- **Short:** Lock-free thread-safe ops via Compare-And-Swap.
- **Detailed:** `AtomicInteger/Long/Reference` use hardware CAS to update without locks. `incrementAndGet`, `compareAndSet`, `accumulateAndGet`. `LongAdder` scales better under high contention.
- **Example:** `counter.incrementAndGet();`

### 13. ReentrantLock vs synchronized?
- **Short:** Lock adds tryLock, fairness, interruptibility, multiple conditions.
- **Detailed:** `synchronized` is simpler and auto-released. `ReentrantLock` allows `tryLock(timeout)`, fairness policy, `lockInterruptibly`, and multiple `Condition`s—at the cost of manual `unlock()` in finally.
- **Example:** `lock.lock(); try{...} finally{ lock.unlock(); }`

### 14. ConcurrentHashMap internals?
- **Short:** Bucket-level CAS + synchronized bins; lock-free reads.
- **Detailed:** No global lock; atomic per-key ops (`compute`, `merge`, `computeIfAbsent`). Weakly consistent iterators. No null keys/values.
- **Example:** Atomic counter map via `merge(key,1,Integer::sum)`.

### 15. wait/notify vs Condition vs BlockingQueue?
- **Short:** Low-level signaling vs lock conditions vs ready-made coordination.
- **Detailed:** `wait/notify` require holding the monitor and a loop guarding spurious wakeups. `Condition` pairs with locks. `BlockingQueue` handles producer/consumer waiting for you.
- **Example:** Prefer `BlockingQueue` for producer/consumer.

---

## Rapid-Fire (Q → A)

1. Thread states? → NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED.
2. sleep vs wait? → sleep keeps lock; wait releases it.
3. wait must be in? → synchronized block.
4. notify vs notifyAll? → Wake one vs all waiters.
5. spurious wakeup fix? → Loop the condition check.
6. join()? → Wait for a thread to die.
7. yield()? → Hint to scheduler.
8. interrupt()? → Request cancellation.
9. isInterrupted()? → Check interrupt flag.
10. InterruptedException meaning? → Blocking call was interrupted.
11. daemon thread? → Doesn't block JVM exit.
12. setDaemon timing? → Before start().
13. thread priority reliable? → No (platform-dependent).
14. main thread daemon? → No.
15. volatile guarantees? → Visibility + ordering, not atomicity.
16. volatile for counters? → Insufficient.
17. double-checked locking needs? → volatile field.
18. happens-before via volatile? → Write before read.
19. final field safe publication? → Yes, if no this-escape.
20. atomicity of long/double? → Non-volatile may tear (pre-JMM guarantees aside).
21. AtomicInteger op? → CAS.
22. LongAdder benefit? → Less contention than AtomicLong.
23. AtomicReference use? → Lock-free object swap.
24. compareAndSet? → Atomic conditional update.
25. ABA problem? → Value changes back; use AtomicStampedReference.
26. ReentrantLock reentrant? → Same thread re-acquires.
27. tryLock benefit? → Avoid blocking/deadlock.
28. fair lock cost? → Lower throughput.
29. lockInterruptibly? → Abort waiting on interrupt.
30. ReadWriteLock? → Many readers or one writer.
31. StampedLock? → Optimistic reads.
32. Condition await/signal? → Lock-based wait/notify.
33. Semaphore? → Permit-limited access.
34. binary semaphore? → Mutex-like (1 permit).
35. CountDownLatch? → One-shot wait for N.
36. CyclicBarrier? → Reusable barrier with action.
37. Phaser? → Flexible multi-phase barrier.
38. Exchanger? → Two threads swap data.
39. CompletableFuture default pool? → Common ForkJoinPool.
40. supplyAsync vs runAsync? → Returns value vs void.
41. thenApply vs thenCompose? → map vs flatMap.
42. thenCombine? → Merge two futures.
43. exceptionally? → Recover from error.
44. handle? → Process result or error.
45. allOf? → Wait for all.
46. anyOf? → First to complete.
47. get() vs join()? → Checked vs unchecked exceptions.
48. Future.cancel? → Attempt cancellation.
49. ForkJoinPool algorithm? → Work-stealing.
50. RecursiveTask vs RecursiveAction? → Returns vs void.
51. fork/join threshold? → Avoid over-splitting.
52. commonPool size? → CPUs - 1 by default.
53. parallelStream pool? → Common ForkJoinPool.
54. Executors.newFixedThreadPool? → Bounded workers.
55. newCachedThreadPool? → Elastic, unbounded.
56. newSingleThreadExecutor? → Serial.
57. newScheduledThreadPool? → Delayed/periodic.
58. newVirtualThreadPerTaskExecutor? → Virtual thread per task.
59. shutdown vs shutdownNow? → Graceful vs interrupt running.
60. awaitTermination? → Block until done/timeout.
61. RejectedExecutionHandler? → Policy when queue full.
62. ThreadPoolExecutor core params? → core/max/keepAlive/queue/handler.
63. Unbounded queue risk? → OOM, ignored max pool.
64. SynchronousQueue use? → Direct handoff pools.
65. ThreadFactory use? → Name/daemon threads.
66. ThreadLocal purpose? → Per-thread state.
67. ThreadLocal leak? → In pools without remove().
68. InheritableThreadLocal? → Child inherits value.
69. ScopedValue (preview)? → Safer ThreadLocal alternative.
70. BlockingQueue put/take? → Block on full/empty.
71. offer/poll timeout? → Bounded waiting.
72. ArrayBlockingQueue? → Bounded array.
73. LinkedBlockingQueue? → Optionally bounded.
74. PriorityBlockingQueue? → Ordered, unbounded.
75. DelayQueue? → Time-delayed elements.
76. ConcurrentLinkedQueue? → Lock-free.
77. CopyOnWriteArrayList? → Snapshot reads.
78. ConcurrentSkipListMap? → Concurrent sorted.
79. produce/consume tool? → BlockingQueue.
80. deadlock detection? → jstack thread dump.
81. livelock? → Active but no progress.
82. starvation? → Thread denied resources.
83. priority inversion? → Low-priority holds lock needed by high.
84. lock ordering? → Prevents circular wait.
85. lock granularity? → Coarse vs fine trade-offs.
86. lock striping? → Multiple locks per structure.
87. optimistic locking? → Version/CAS, retry.
88. pessimistic locking? → Lock upfront.
89. immutable + concurrency? → Inherently thread-safe.
90. thread-safe singleton? → enum/holder idiom.
91. safe publication? → final, volatile, synchronized, concurrent collection.
92. data race definition? → Unsynchronized conflicting access.
93. memory visibility issue? → Stale cached values.
94. piggybacking? → Reuse existing happens-before.
95. why not Thread.stop? → Unsafe (deprecated).
96. cooperative cancellation? → Interrupt + checks.
97. busy-wait downside? → Wastes CPU.
98. backoff strategy? → Reduce contention.
99. false sharing? → Cache line contention.
100. @Contended? → Pads to avoid false sharing.
101. virtual thread carrier? → Platform thread it runs on.
102. pinning cause? → synchronized/native during block.
103. pinning fix? → ReentrantLock.
104. virtual thread for CPU work? → No benefit.
105. structured concurrency (preview)? → Treat tasks as a unit.
106. thread per request model? → Scales with virtual threads.
107. CompletableFuture vs virtual threads? → Async pipelines vs simple blocking code.
108. blocking call on FJP? → Starves pool (use managedBlocker).
109. ManagedBlocker? → Tell FJP about blocking.
110. concurrent counter best? → LongAdder.
111. AtomicLong vs LongAdder? → Single var vs striped cells.
112. thread confinement? → Keep data in one thread.
113. stack confinement? → Local variables.
114. immutable object publication? → Always safe.
115. happens-before of thread start? → start() before run actions.
116. happens-before of join? → run actions before join returns.
117. double-checked locking pattern? → volatile + null check twice.
118. when to use synchronized? → Simple mutual exclusion.
119. when to use locks? → Need tryLock/conditions/fairness.
120. when to use atomics? → Single-variable counters/flags.
121. when to use concurrent collections? → Shared maps/queues.
122. when to use CompletableFuture? → Async composition.
123. when to use virtual threads? → High-concurrency blocking I/O.
124. when to use ForkJoin? → Recursive CPU-bound splitting.
125. CountDownLatch reuse? → No (one-shot).
126. CyclicBarrier reuse? → Yes.
127. Phaser dynamic parties? → Yes.
128. Semaphore release without acquire? → Adds permits.
129. fairness in semaphore? → Optional FIFO.
130. blocking vs non-blocking algorithm? → Locks vs CAS/lock-free.
131. lock-free vs wait-free? → Some progress vs guaranteed per-op progress.
132. memory barrier? → Orders memory operations.
133. store/load barrier? → Ordering primitives.
134. volatile read cost? → Cheap read, ordered.
135. contended lock cost? → Context switches.
136. thread dump shows? → Stacks, lock holders, deadlocks.
137. CPU 100% one thread? → Likely busy loop.
138. high context switching? → Too many threads/contention.
139. tuning pool size (CPU-bound)? → ~#cores.
140. tuning pool size (I/O-bound)? → Higher / virtual threads.
141. Little's law use? → Concurrency = throughput × latency.
142. graceful shutdown steps? → shutdown, awaitTermination, shutdownNow.
143. handle InterruptedException? → Restore flag or propagate.
144. swallow interrupt? → Anti-pattern.
145. synchronized on String/Integer? → Bad (shared/cached).
146. synchronized on this leak? → Exposes lock; use private lock.
147. double locking on different monitors? → Deadlock risk.
148. concurrency testing tools? → jcstress, stress tests.
149. reproduce race? → Hard; use stress + invariants.
150. golden rule? → Prefer immutability and high-level concurrency utilities over low-level locks.
