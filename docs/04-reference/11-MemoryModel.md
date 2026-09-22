# Java Memory Model (`pkg16advconcurrency`)

Deep concurrency beyond threads — synchronizers, visibility, structured concurrency.

## Advanced synchronizers (`pkg16advconcurrency`)

| Class | Use |
|-------|-----|
| `CountDownLatch` | one-shot wait until N events |
| `CyclicBarrier` | reusable barrier for N parties |
| `Semaphore` | limit concurrent access (permits) |
| `Phaser` | dynamic-party phased barrier |
| `Exchanger` | two-thread object swap |

## Java Memory Model (JMM)

Defines **when writes by one thread are visible to another**.

### happens-before rules (key edges)

- Unlock → subsequent lock (same monitor)
- `volatile` write → `volatile` read (same field)
- Thread start → actions in started thread
- Actions in thread → `join()` returns

### volatile

Forces reads/writes through main memory — no stale cached values.

### Safe publication

- `final` fields visible after constructor completes
- volatile reference to fully constructed object
- static initializer (class holder pattern)

### Classic bug: broken double-checked locking

Without `volatile` on the reference field, another thread may see a partially constructed object.

## Structured Concurrency (Java 21+)

`StructuredTaskScope` owns child task lifetimes — cancel siblings on failure or first success.

| Policy | Behavior |
|--------|----------|
| `ShutdownOnSuccess` | first success wins; cancel others |
| `ShutdownOnFailure` | any failure cancels all |

See `advconcurrency7StructuredConcurrency.java`.

## Files

`advconcurrency1` → `advconcurrency7` in [`pkg16advconcurrency`](../../pkg16advconcurrency).
