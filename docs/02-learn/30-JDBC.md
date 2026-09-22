# 30 — JDBC & Databases

**Previous:** [29 Networking](29-Networking.md) · **Next:** [31 REST APIs](31-RestAPIs.md)

▶️ `pkg11jdbc/jdbc1JdbcOverview.java` → `jdbc5ConnectionPooling.java`

---

## JDBC flow

```
DriverManager.getConnection(url, user, pass)
    → Connection
    → PreparedStatement ("SELECT * FROM users WHERE id = ?")
    → setInt(1, id)
    → executeQuery() → ResultSet
    → commit() / rollback()
    → close (try-with-resources)
```

---

## Golden rules

| Rule | Why |
|------|-----|
| Use `PreparedStatement` | SQL injection prevention + plan cache |
| Use connection pool (HikariCP) | Creating connections is expensive |
| Manage transactions explicitly | `setAutoCommit(false)` when needed |
| Close resources | try-with-resources |

---

## Class order

| # | File | Topic |
|---|------|-------|
| 1 | `jdbc1JdbcOverview` | JDBC architecture |
| 2 | `jdbc2JdbcBasics` | CRUD with PreparedStatement |
| 3 | `jdbc3Transactions` | commit / rollback |
| 4 | `jdbc4BatchProcessing` | Batch inserts |
| 5 | `jdbc5ConnectionPooling` | Pool concepts |

**Full guide →** [JDBC](../04-reference/06-JDBC.md) · [16 JDBC, JPA & Hibernate](../03-interview/16-JdbcJpaHibernate.md)

**Next →** [31 REST APIs](31-RestAPIs.md)
