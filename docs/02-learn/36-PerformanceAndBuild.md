# 36 — Performance & Build Tools

**Previous:** [35 Serialization](35-Serialization.md) · **Next:** [37 Interview Prep](37-InterviewPrep.md)

▶️ `pkg19performance/` · `build/maven/` · `build/gradle/`

---

## Performance toolkit

| Tool | Purpose |
|------|---------|
| **JFR** (Flight Recorder) | Low-overhead production profiling |
| **JMH** | Reliable microbenchmarks |
| **GC logs** | `-Xlog:gc*` — allocation and pause analysis |
| **GraalVM native-image** | Fast startup, smaller footprint |

```bash
# Run JMH benchmark in pkg19performance/jmh-demo
mvn package -f pkg19performance/jmh-demo/pom.xml
```

---

## Resilience patterns (hand-coded)

`pkg18resiliencepatterns/` — no framework required:
- Circuit breaker
- Retry with backoff
- Bulkhead
- Rate limiter

---

## Build tools

| Tool | File | Learn |
|------|------|-------|
| **Maven** | `build/maven/simple-app` | `pom.xml`, lifecycle, dependencies |
| **Maven multi-module** | `build/maven/multi-module` | Parent POM, modules |
| **Gradle** | `build/gradle/simple-app` | `build.gradle.kts` |

```bash
mvn test -f build/maven/simple-app/pom.xml
```

**Guides →** [Build Tools](../04-reference/13-BuildTools.md) · [19 Performance](../03-interview/19-Performance.md)

**Next →** [37 Interview Prep](37-InterviewPrep.md)
