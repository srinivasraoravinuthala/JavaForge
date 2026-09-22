# System Design Fundamentals — Interview Questions (80+)

## Detailed Questions

### 1. How do you approach a system design interview?
- **Short:** Clarify → estimate → high-level → deep-dive → bottlenecks → trade-offs.
- **Detailed:** (1) Clarify functional + non-functional requirements (scale, latency, consistency). (2) Back-of-envelope estimates (QPS, storage, bandwidth). (3) Draw a high-level architecture (clients, API, services, DB, cache, queue). (4) Deep-dive a component. (5) Identify bottlenecks and scale them. (6) Discuss trade-offs and failure modes.
- **Example:** Design a URL shortener: hashing, DB schema, cache, redirect path.

### 2. Vertical vs horizontal scaling?
- **Short:** Bigger machine vs more machines.
- **Detailed:** Vertical (scale-up) is simple but bounded and a single point of failure. Horizontal (scale-out) adds nodes behind a load balancer—needs statelessness, partitioning, and coordination but scales further and improves availability.
- **Example:** Add app servers behind an LB; shard the DB.

### 3. Explain the CAP theorem.
- **Short:** Under a partition, choose Consistency or Availability.
- **Detailed:** A distributed store can't simultaneously guarantee Consistency, Availability, and Partition tolerance. Partitions happen, so you trade C vs A. CP systems reject some requests to stay consistent; AP systems stay available but may serve stale data (eventual consistency).
- **Example:** ZooKeeper (CP) vs Dynamo/Cassandra (AP).

### 4. What is consistent hashing and why use it?
- **Short:** Maps keys/nodes on a ring to minimize remapping on changes.
- **Detailed:** With plain modulo hashing, adding/removing a node remaps most keys. Consistent hashing places nodes and keys on a ring; only keys between the changed node and its predecessor move. Virtual nodes balance load.
- **Example:** Distributed caches/sharded stores.

### 5. Caching strategies?
- **Short:** Cache-aside, read-through, write-through, write-back.
- **Detailed:** Cache-aside: app loads on miss and populates. Read-through: cache loads from DB. Write-through: write to cache+DB synchronously. Write-back: write to cache, async to DB (fast, risk of loss). Use TTLs and eviction (LRU/LFU). Beware stampede and stale data.
- **Example:** Redis cache-aside in front of a SQL DB.

### 6. SQL vs NoSQL?
- **Short:** Relational/ACID vs flexible/scalable/eventually-consistent.
- **Detailed:** SQL: strong schema, joins, ACID transactions, vertical scaling, great for complex queries/integrity. NoSQL: key-value/document/column/graph, horizontal scaling, flexible schema, often eventual consistency—great for scale and simple access patterns.
- **Example:** Orders/payments → SQL; session store/feed → NoSQL.

### 7. How do message queues help?
- **Short:** Decouple producers/consumers, smooth load, enable async.
- **Detailed:** Queues (Kafka, RabbitMQ, SQS) buffer work, provide backpressure, retries, and durability, and let services scale independently. Choose at-least-once vs exactly-once semantics; design idempotent consumers.
- **Example:** Order service publishes events; email/inventory consume them.

### 8. How do you design for reliability/availability?
- **Short:** Redundancy, failover, retries, timeouts, circuit breakers.
- **Detailed:** Eliminate single points of failure (multi-AZ/region), health checks + automatic failover, retries with exponential backoff + jitter, timeouts, bulkheads, circuit breakers, and graceful degradation.
- **Example:** Replica DB promoted on primary failure.

### 9. Database scaling techniques?
- **Short:** Replication, partitioning/sharding, indexing, caching.
- **Detailed:** Read replicas scale reads; sharding splits data across nodes by a key (hash/range/geo). Add indexes for query speed (cost on writes). Cache hot reads. Use CDC for derived stores.
- **Example:** Shard users by user_id hash; replicas for analytics.

### 10. Idempotency and why it matters?
- **Short:** Same request applied once even if retried.
- **Detailed:** Network retries can duplicate requests; idempotency keys/dedup ensure exactly-once effects (e.g., don't charge twice). Use unique request IDs and upserts.
- **Example:** Payment with an idempotency key.

---

## Rapid-Fire (Q → A)

1. Latency vs throughput? → Time per op vs ops per second.
2. Availability target? → e.g. 99.9% ("three nines").
3. 99.99% downtime/year? → ~52 minutes.
4. SLA vs SLO vs SLI? → Agreement vs objective vs indicator.
5. Load balancer role? → Distribute traffic.
6. LB algorithms? → Round-robin, least-conn, hashing.
7. L4 vs L7 LB? → Transport vs application layer.
8. Reverse proxy? → Fronting server (nginx).
9. CDN purpose? → Cache static content near users.
10. Stateless service benefit? → Easy horizontal scaling.
11. Sticky sessions downside? → Hurts scaling/failover.
12. Session store? → Redis/DB for shared state.
13. Sharding? → Partition data across nodes.
14. Shard key choice? → Even distribution, avoid hotspots.
15. Replication? → Copies for reads/HA.
16. Leader-follower? → Writes to leader, reads from followers.
17. Replication lag? → Stale reads on followers.
18. Quorum? → Majority for read/write consistency.
19. CAP under partition? → Pick C or A.
20. PACELC? → Else latency vs consistency.
21. ACID? → Atomicity, Consistency, Isolation, Durability.
22. BASE? → Basically Available, Soft state, Eventual.
23. Eventual consistency? → Converges over time.
24. Strong consistency? → Reads see latest write.
25. Read-your-writes? → See your own updates.
26. Optimistic concurrency? → Version check on write.
27. Pessimistic locking? → Lock before update.
28. Index trade-off? → Faster reads, slower writes.
29. B-tree index? → Range queries.
30. Hash index? → Equality lookups.
31. Denormalization? → Duplicate for read speed.
32. Normalization? → Reduce redundancy.
33. OLTP vs OLAP? → Transactions vs analytics.
34. Data warehouse? → Analytics store.
35. Cache eviction? → LRU/LFU/TTL.
36. Cache stampede fix? → Locking/request coalescing.
37. Write-through? → Sync cache+DB.
38. Write-back? → Async to DB.
39. Cache-aside? → App manages cache.
40. CDN cache invalidation? → Hard problem; versioned URLs.
41. Message queue benefit? → Decoupling/async.
42. Pub/sub? → Fan-out to subscribers.
43. At-least-once? → May duplicate.
44. At-most-once? → May drop.
45. Exactly-once? → Hard; idempotency + dedup.
46. Kafka core concept? → Partitioned log.
47. Backpressure? → Slow consumer signals producer.
48. Dead-letter queue? → Failed messages.
49. Idempotency key? → Dedup requests.
50. Rate limiting? → Throttle requests.
51. Token bucket? → Allow bursts up to capacity.
52. Leaky bucket? → Smooth constant rate.
53. Circuit breaker? → Stop calling failing service.
54. Bulkhead? → Isolate failures.
55. Timeout importance? → Avoid hanging.
56. Retry with backoff? → Exponential + jitter.
57. Thundering herd? → Synchronized retries overload.
58. Health check? → Liveness/readiness.
59. Graceful degradation? → Reduced functionality on failure.
60. Blue-green deploy? → Two environments switch.
61. Canary deploy? → Gradual rollout.
62. Feature flag? → Toggle features.
63. Microservices benefit? → Independent deploy/scale.
64. Microservices cost? → Distributed complexity.
65. Monolith benefit? → Simplicity.
66. API gateway? → Single entry, routing/auth.
67. Service discovery? → Locate service instances.
68. Saga pattern? → Distributed transactions via steps.
69. 2PC? → Two-phase commit (blocking).
70. Outbox pattern? → Reliable event publishing.
71. CQRS? → Separate read/write models.
72. Event sourcing? → Store events as truth.
73. CDC? → Change Data Capture.
74. Hot partition? → Skewed load.
75. Geo-replication? → Multi-region copies.
76. Multi-region active-active? → All regions serve writes.
77. Data locality? → Keep data near compute.
78. Estimating QPS? → Users × actions / time.
79. Estimating storage? → Records × size × retention.
80. Designing for 10x growth? → Stateless, shard, cache, queue.
81. Observability pillars? → Logs, metrics, traces.
82. Golden rule? → Start simple; scale the proven bottleneck; justify trade-offs.
