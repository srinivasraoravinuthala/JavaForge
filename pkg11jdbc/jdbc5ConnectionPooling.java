package pkg11jdbc;

/*
 * jdbc5ConnectionPooling.java
 * ---------------------------
 * Connection pooling & DataSource: how production apps manage DB connections.
 *
 * DEFINITION:
 *   Opening a DB connection is expensive (TCP + auth + session setup). A
 *   connection pool keeps a set of open connections and hands them out on
 *   demand, returning them to the pool on close() instead of tearing them down.
 *
 * KEY POINTS:
 *   - Prefer javax.sql.DataSource over DriverManager in real apps.
 *   - Popular pools: HikariCP (default in Spring Boot), Apache DBCP, c3p0.
 *   - Tune: max pool size, min idle, connection timeout, max lifetime.
 *   - "close()" on a pooled connection RETURNS it to the pool (doesn't close it).
 *
 * This file is conceptual (no external pool jar in this repo). It prints the
 * canonical HikariCP setup so you can copy it into a real project.
 */
public class jdbc5ConnectionPooling {

    public static void main(String[] args) {
        System.out.println("Why pool? Open/close per request is slow and exhausts the DB.");
        System.out.println("A pool reuses a fixed set of warm connections.\n");

        System.out.println("DataSource vs DriverManager:");
        System.out.println("  DriverManager.getConnection(url)  -> new physical connection each time");
        System.out.println("  dataSource.getConnection()        -> borrow from the pool, return on close\n");

        System.out.println("Typical HikariCP setup (add HikariCP + driver jars):");
        System.out.println("""
              HikariConfig cfg = new HikariConfig();
              cfg.setJdbcUrl("jdbc:postgresql://localhost:5432/shop");
              cfg.setUsername("app");
              cfg.setPassword("secret");
              cfg.setMaximumPoolSize(10);        // cap concurrent connections
              cfg.setMinimumIdle(2);             // keep a few warm
              cfg.setConnectionTimeout(30_000);  // ms to wait for a free connection
              cfg.setMaxLifetime(1_800_000);     // recycle after 30 min

              try (HikariDataSource ds = new HikariDataSource(cfg);
                   Connection c = ds.getConnection();          // borrow
                   PreparedStatement ps = c.prepareStatement("SELECT 1")) {
                  ps.executeQuery();
              }                                                  // close() returns it to the pool""");

        System.out.println("\nRule of thumb: pool size ~= (core_count * 2) for CPU-bound,");
        System.out.println("higher for I/O-bound workloads. Measure, then tune.");
    }
}
