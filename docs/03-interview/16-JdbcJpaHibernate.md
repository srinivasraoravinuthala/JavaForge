# JDBC, JPA & Hibernate — Interview Questions (82+)

See [`pkg11jdbc`](../../pkg11jdbc).

---

## Detailed Questions

### 1. JDBC core steps?
- **Short:** Load driver → get Connection → create Statement/PreparedStatement → execute → process ResultSet → close.
- **Detailed:** Modern JDBC 4+ auto-loads drivers. Always use try-with-resources. `PreparedStatement` binds parameters (prevents SQL injection, enables plan cache). `ResultSet` cursor over rows.
- **Example:** `jdbc2JdbcBasics` in pkg11jdbc.

### 2. Statement vs PreparedStatement vs CallableStatement?
- **Short:** Statement static SQL; Prepared parameterized; Callable stored procedures.
- **Detailed:** Never concatenate user input into Statement. `setString(1, x)` binds safely. Callable `registerOutParameter` for OUT params.
- **Example:** `SELECT * FROM users WHERE id = ?`

### 3. Connection pooling why?
- **Short:** Creating TCP+auth connections is expensive; pool reuses them.
- **Detailed:** HikariCP tracks idle, max pool size, connection timeout, leak detection. Size pool from DB max connections / app instances. Without pool, latency and DB overload under load.
- **Example:** `jdbc5ConnectionPooling.java`.

### 4. Transaction management in JDBC?
- **Short:** `setAutoCommit(false)`, work, `commit()` or `rollback()`.
- **Detailed:** One connection per transaction typically. Isolation levels `READ_COMMITTED`, `REPEATABLE_READ`, `SERIALIZABLE`. Spring `@Transactional` automates this.
- **Example:** Transfer money: debit + credit in one transaction.

### 5. JPA vs Hibernate vs JDBC?
- **Short:** JDBC low-level SQL; JPA spec/API; Hibernate JPA implementation + extras.
- **Detailed:** JPA maps entities to tables (`@Entity`, `@Table`). Hibernate provides Session API, caching, dialects. JDBC when you need full SQL control or bulk ops.
- **Example:** `EntityManager.persist(user)` vs `jdbcTemplate.update(...)`.

### 6. EntityManager vs Session?
- **Short:** `EntityManager` is JPA standard; `Session` is Hibernate native extension of same idea.
- **Detailed:** In Hibernate, `sessionFactory.getCurrentSession()` integrates with transactions. `EntityManager` from `EntityManagerFactory`. Persistence context holds managed entities.
- **Example:** `em.find(User.class, id)` — first-level cache hit if managed.

### 7. Entity lifecycle states?
- **Short:** New/transient, managed/persistent, detached, removed.
- **Detailed:** `persist` → managed. `close` EM → detached. `merge` reattaches detached copy. `remove` schedules delete on flush. Changes to managed entities auto-dirty-checked on flush.
- **Example:** Modify managed `user.setName()` — SQL UPDATE on commit without explicit update call.

### 8. Lazy vs eager loading?
- **Short:** `FetchType.LAZY` loads association on access; `EAGER` loads immediately.
- **Detailed:** Default `@ManyToOne` eager, `@OneToMany` lazy. Lazy needs open persistence context (or fetch join). Eager causes cartesian product / over-fetching.
- **Example:** `user.getOrders().size()` triggers lazy load if session open.

### 9. N+1 query problem?
- **Short:** One query for parents + N queries for each child association.
- **Detailed:** Loading 100 users then lazy-loading each user's orders = 101 queries. Fix: `JOIN FETCH`, `@EntityGraph`, batch fetching (`@BatchSize`), or DTO projection query.
- **Example:** `SELECT u FROM User u JOIN FETCH u.orders`

### 10. First vs second level cache?
- **Short:** L1 = persistence context per EM/session; L2 = shared session factory cache (entities).
- **Detailed:** L1 always on for managed entities. L2 needs provider config (Ehcache, Infinispan); cache entity by id across sessions. Query cache separate. Stale data risk — tune TTL and invalidation.
- **Example:** `find` by same id twice in one tx — one SELECT (L1).

### 11. JPQL vs native SQL?
- **Short:** JPQL object-oriented (`SELECT u FROM User u`); native SQL database-specific.
- **Detailed:** JPQL uses entity names and fields. Native for reporting, bulk, DB-specific features. `createNativeQuery` maps to entities or scalars.
- **Example:** `em.createQuery("SELECT u FROM User u WHERE u.active = true")`.

### 12. Optimistic vs pessimistic locking?
- **Short:** Optimistic `@Version` column check on update; pessimistic `SELECT FOR UPDATE` locks row.
- **Detailed:** Optimistic good for low contention — `OptimisticLockException` on conflict. Pessimistic `LockModeType.PESSIMISTIC_WRITE` holds DB lock during transaction.
- **Example:** `@Version Long version` incremented each update.

---

## Rapid-Fire (Q → A)

1. DriverManager vs DataSource? → DataSource preferred with pool.
2. SQLException checked? → Yes in raw JDBC.
3. SQL injection fix? → PreparedStatement bind params.
4. setObject? → Generic parameter binding.
5. executeQuery vs executeUpdate? → SELECT vs DML.
6. Generated keys? → Statement.RETURN_GENERATED_KEYS.
7. Batch update? → addBatch executeBatch.
8. Fetch size ResultSet? → Hints driver batch fetch.
9. Scrollable ResultSet? → TYPE_SCROLL_INSENSITIVE.
10. holdability? → CLOSE_CURSORS_AT_COMMIT.
11. savepoint? → Partial rollback.
12. Isolation READ_UNCOMMITTED? → Dirty reads possible.
13. READ_COMMITTED default? → Many DBs default.
14. REPEATABLE_READ? → Same row reads consistent.
15. SERIALIZABLE? → Strictest; phantom risk reduced.
16. Phantom read? → New rows appear in range.
17. Dirty read? → Uncommitted data seen.
18. Non-repeatable read? → Row changes between reads.
19. ACID? → Atomicity, Consistency, Isolation, Durability.
20. CAP in databases? → Consistency, Availability, Partition tolerance.
21. @Entity required? → JPA managed class marker.
22. @Id? → Primary key.
23. @GeneratedValue strategies? → IDENTITY, SEQUENCE, TABLE, AUTO.
24. @Column? → Name, nullable, length mapping.
25. @Transient? → Not persisted field.
26. @Embeddable? → Value type in entity.
27. @EmbeddedId? → Composite key embed.
28. @ManyToOne join column? → FK column.
29. @OneToMany mappedBy? → Inverse side of bidirectional.
30. CascadeType ALL? → Propagate persist/remove etc.
31. orphanRemoval? → Delete children removed from collection.
32. @JoinTable? → Many-to-many link table.
33. equals/hashCode JPA? → Business key or id; avoid collections.
34. toString JPA? → Avoid lazy collections in toString.
35. ddl-auto none? → Production use migrations.
36. ddl-auto validate? → Schema matches entities only.
37. Flyway version table? → flyway_schema_history.
38. Liquibase changelog? → XML/YAML changesets.
39. Persistence unit? → persistence.xml config (less in Boot).
40. EntityManagerFactory scope? → One per app typically.
41. Thread-local session pattern? → Hibernate classic per thread.
42. Stateless session? → Bulk ops no cache.
43. getReference vs find? → Lazy proxy vs immediate load.
44. persist vs merge? → New vs detached reattach.
45. flush mode AUTO? → Flush before query/commit.
46. COMMIT flush mode? → Flush only on commit.
47. @Modifying query? → Update/delete JPQL needs @Transactional.
48. clearAutomatically? → Clear persistence context after bulk.
49. Pagination JPA? → setFirstResult setMaxResults.
50. Page Spring Data? → Pageable Page<T> return.
51. Sort Spring Data? → Sort.by("name").
52. Specification? → Predicate builder dynamic where.
53. Criteria API Root? → From clause entity.
54. Metamodel _ class? → Generated static fields for attrs.
55. Hibernate @SQLRestriction? → Filter clause on entity.
56. @Filter? → Dynamic enable/disable filter.
57. @Formula? → Derived column read-only.
58. @SecondaryTable? → Extra table mapping.
59. Inheritance SINGLE_TABLE? → Discriminator column.
60. JOINED strategy? → Table per subclass normalized.
61. TABLE_PER_CLASS? → Table per concrete class.
62. @MappedSuperclass? → Shared fields not entity.
63. AttributeConverter? → Custom type DB mapping.
64. JSON column mapping? → Hibernate Types / converter.
65. Envers auditing? → Hibernate revision tables.
66. Interceptor? → Hibernate callbacks low-level.
67. Event listener? → JPA @PrePersist etc.
68. @PreUpdate? → Before update SQL.
69. Bean Validation @Email on entity? → Validates before persist.
70. Schema multitenancy? → Separate schema per tenant.
