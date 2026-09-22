package pkg11jdbc;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/*
 * jdbc1JdbcOverview.java
 * ----------------------
 * JDBC (Java Database Connectivity): the standard API for relational databases.
 *
 * DEFINITION:
 *   JDBC is a vendor-neutral API (java.sql) for connecting to SQL databases,
 *   running statements, and reading results. Each database ships a "driver" jar
 *   that implements the API for its wire protocol.
 *
 * THE 5 STEPS:
 *   1. (Java 6+) Driver auto-loads from the classpath via the ServiceLoader.
 *   2. Connection conn = DriverManager.getConnection(url, user, pass);
 *   3. Statement / PreparedStatement to send SQL.
 *   4. ResultSet to read rows back.
 *   5. close() everything (use try-with-resources).
 *
 * JDBC URL FORMAT:  jdbc:<subprotocol>:<subname>
 *   jdbc:postgresql://localhost:5432/mydb
 *   jdbc:mysql://localhost:3306/mydb
 *   jdbc:sqlite:app.db                (file)   |   jdbc:h2:mem:test (in-memory)
 *
 * NOTE: This repo has no external jars, so no driver is registered by default.
 *       Add one (e.g. sqlite-jdbc.jar) and run:
 *       java -cp ".;sqlite-jdbc.jar" pkg11jdbc/jdbc1JdbcOverview.java
 */
public class jdbc1JdbcOverview {

    public static void main(String[] args) {
        System.out.println("Registered JDBC drivers on the classpath:");
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        int count = 0;
        while (drivers.hasMoreElements()) {
            Driver d = drivers.nextElement();
            System.out.printf("  - %s (v%d.%d)%n",
                    d.getClass().getName(), d.getMajorVersion(), d.getMinorVersion());
            count++;
        }
        if (count == 0) {
            System.out.println("  (none) — add a driver jar to the classpath to connect.");
        }

        System.out.println("\nExample JDBC URLs:");
        for (String url : new String[]{
                "jdbc:postgresql://localhost:5432/shop",
                "jdbc:mysql://localhost:3306/shop",
                "jdbc:sqlite:shop.db",
                "jdbc:h2:mem:shop"}) {
            System.out.println("  " + url);
        }

        System.out.println("\nThe canonical try-with-resources pattern:");
        System.out.println("""
              try (Connection c = DriverManager.getConnection(url, user, pass);
                   PreparedStatement ps = c.prepareStatement("SELECT * FROM users WHERE id = ?")) {
                  ps.setInt(1, 42);
                  try (ResultSet rs = ps.executeQuery()) {
                      while (rs.next()) System.out.println(rs.getString("name"));
                  }
              }""");
    }
}
