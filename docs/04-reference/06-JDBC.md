# JDBC & Databases (`pkg11jdbc`)

Runnable demos: [`pkg11jdbc`](../../pkg11jdbc). Run with `java pkg11jdbc/jdbc1JdbcOverview.java`.

> This repo has **no external jars**, so no JDBC driver is registered by default.
> The CRUD/transaction demos run for real **if** an in-memory driver is on the
> classpath, and otherwise print the exact SQL + setup steps. To run them live:
> ```bash
> # download h2.jar, then:
> java -cp ".;h2.jar" pkg11jdbc/jdbc2CrudStatements.java   # Windows (use ':' on macOS/Linux)
> ```

## What is JDBC?
JDBC (`java.sql`) is a **vendor-neutral API** for relational databases. Each DB
ships a *driver* implementing the API for its wire protocol.

## The lifecycle
```
DriverManager / DataSource ──► Connection ──► Statement/PreparedStatement ──► ResultSet ──► close()
```

## JDBC URLs
```
jdbc:postgresql://host:5432/db
jdbc:mysql://host:3306/db
jdbc:sqlite:app.db          (file)
jdbc:h2:mem:test            (in-memory)
```

## Files in this package
| # | File | Topic |
|---|------|-------|
| 1 | `jdbc1JdbcOverview` | drivers, URLs, the canonical pattern |
| 2 | `jdbc2CrudStatements` | Create/Read/Update/Delete, `PreparedStatement` |
| 3 | `jdbc3ResultSetAndMetadata` | iterating rows, `ResultSetMetaData` |
| 4 | `jdbc4TransactionsAndBatch` | commit/rollback, batch updates |
| 5 | `jdbc5ConnectionPooling` | `DataSource`, HikariCP, pool tuning |

## Best practices
- **Always** use `PreparedStatement` with `?` params — never concatenate user input (SQL injection!).
- Wrap everything in **try-with-resources** so connections/statements/result sets close.
- Group related writes in a **transaction**: `setAutoCommit(false)` → work → `commit()` / `rollback()`.
- **Batch** bulk inserts (`addBatch`/`executeBatch`) to cut round trips.
- In production use a **connection pool** (HikariCP) via a `DataSource`, not `DriverManager`.
