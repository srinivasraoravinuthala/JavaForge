# 39 — Resilience Patterns

**Previous:** [38 Metaprogramming](38-Metaprogramming.md) · **Next:** [40 Spring Boot Intro](40-SpringBootIntro.md)

▶️ `java pkg18resiliencepatterns/resilience1CircuitBreaker.java` · `resilience4RateLimiter.java`

---

## Why resilience patterns?

Distributed systems fail. These patterns keep your service **available** when dependencies are slow or down.

| Pattern | Purpose |
|---------|---------|
| **Circuit breaker** | Stop calling a failing service; fail fast |
| **Retry with backoff** | Retry transient errors with increasing delay |
| **Bulkhead** | Isolate thread pools so one failure doesn't sink all |
| **Rate limiter** | Cap requests per time window |

---

## Circuit breaker states

```
CLOSED → (failures exceed threshold) → OPEN → (timeout) → HALF-OPEN → (success) → CLOSED
```

Run: `java pkg18resiliencepatterns/resilience1CircuitBreaker.java`

---

## When to use what

- **Retry:** Network blips, 503 responses, idempotent operations
- **Circuit breaker:** Downstream service consistently failing
- **Bulkhead:** Mixed workloads (payments vs. analytics)
- **Rate limiter:** Protect APIs from abuse or overload

Production libraries: Resilience4j, Sentinel, Hystrix (legacy).

**Interview drill →** [18 System Design](../03-interview/18-SystemDesign.md)

**Next →** [40 Spring Boot Intro](40-SpringBootIntro.md)
