# Roadmap: Beginner → Principal Engineer

```
Beginner ──► Intermediate ──► Senior ──► Tech Lead ──► Architect ──► Principal
```

## Stage 1 — Beginner
- Syntax, data types, operators, control flow, loops, methods, arrays, strings.
- OOP: classes, objects, encapsulation, inheritance, polymorphism, abstraction.
- Tooling: `javac`, `java`, classpath, packages.

## Stage 2 — Intermediate
- Collections Framework (List/Set/Map/Queue/Deque) and when to use which.
- Generics, bounded wildcards, type erasure.
- Exceptions: checked vs unchecked, try-with-resources, custom exceptions.
- Functional programming: lambdas, method references, `java.util.function`.
- Streams API & Collectors. `Optional`. `record`, `enum`, `sealed`.

## Stage 3 — Senior
- DSA: arrays, linked lists, stacks, queues, heaps, hashing, trees, BST, AVL, tries, graphs, union-find.
- Algorithms: sorting, searching, two-pointers, sliding window, recursion, DP, greedy, backtracking, graph traversal.
- JVM basics: class loading, runtime data areas, heap vs stack, GC fundamentals.
- Concurrency: threads, `Runnable`/`Callable`, `ExecutorService`, `Future`, synchronization.
- Testing mindset, complexity analysis (Big-O).

## Stage 4 — Tech Lead
- Advanced concurrency: `CompletableFuture`, `ForkJoinPool`, locks, atomics, concurrent collections, virtual threads.
- All 23 GoF design patterns; SOLID; clean code.
- Performance profiling, memory leaks, GC tuning.
- Code review, mentoring, API design.

## Stage 5 — Architect
- System design: scalability, availability, consistency, partitioning, caching, queues.
- JVM tuning across GCs (G1, ZGC, Shenandoah); latency vs throughput.
- Distributed systems fundamentals, CAP, idempotency, backpressure.
- Trade-off analysis and documentation (ADRs).

## Stage 6 — Principal
- Org-wide technical strategy, platform thinking, build-vs-buy.
- Deep JVM/runtime expertise; influence across teams.
- Drive standards, reliability, and long-term architecture.

## Applied Java (build real things) — `pkg9io` → `pkg13libs`
- **I/O:** byte/char streams, NIO.2 (`Path`/`Files`), serialization, try-with-resources.
- **Networking:** TCP/UDP sockets, `URL`, modern `HttpClient` (sync + async).
- **Databases:** JDBC lifecycle, `PreparedStatement`, transactions, batching, connection pooling.
- **REST APIs:** design (verbs/URLs/status codes), build a server, consume with `HttpClient`, JSON.
- **Standard libraries:** `java.time`, regex, crypto/hashing, `BigDecimal`, reflection, annotations, logging.

## Ecosystem fundamentals — `pkg14` → `pkg20`, `build/`
- **Testing:** JUnit 5, Mockito, AssertJ, TDD, parameterized, property-based (jqwik).
- **JPMS & SPI:** modules, ServiceLoader, `module-info.java`.
- **Advanced concurrency:** JMM, latches/barriers/semaphores, structured concurrency.
- **Metaprogramming:** dynamic proxies, MethodHandles, VarHandles, annotation processing.
- **Resilience patterns:** circuit breaker, retry+backoff, bulkhead, rate limiter (pure Java).
- **Performance:** GC tuning, JFR, GraalVM native-image, JMH benchmarks.
- **Serialization:** Jackson, JAXB, YAML, Protobuf, Avro.
- **Build:** Maven lifecycle, multi-module, BOM; Gradle basics.

## Portfolio projects (ship these)

- [ ] [Console Gradebook](../../projects/01-console-gradebook/README.md) (after ch.09)
- [ ] [Library OOP](../../projects/02-library-oop/README.md) (after ch.15)
- [ ] [Todo REST](../../projects/03-todo-rest/README.md) (after ch.31)
- [ ] [Notes API — Spring Boot](../../projects/04-notes-api/README.md) (after ch.40)
- [ ] [Notes Web — HTML + API](../../projects/05-notes-web/README.md) (after ch.41)

## Skill Checklist (tick as you master)
- [ ] Can implement every DSA structure from memory.
- [ ] Can explain GC algorithms and choose one for a workload.
- [ ] Can write correct concurrent code and reason about happens-before.
- [ ] Can apply and critique design patterns.
- [ ] Can design a scalable system and defend the trade-offs.
- [ ] Can read/write files with streams and NIO.2.
- [ ] Can build and consume a REST API over HTTP.
- [ ] Can do safe, transactional database access with JDBC.
- [ ] Can write unit tests with JUnit 5 + Mockito; understand TDD and property-based testing.
- [ ] Can implement resilience patterns (circuit breaker, retry) by hand.
- [ ] Can choose serialization format and use Jackson for JSON in production code.
- [ ] Can build and structure Maven multi-module projects.
- [ ] Can run a Spring Boot service and call it from a browser UI.
- [ ] Can answer 1000+ interview questions confidently.
