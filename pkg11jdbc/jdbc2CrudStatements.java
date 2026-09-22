package pkg11jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * jdbc2CrudStatements.java
 * ------------------------
 * CRUD with Statement vs PreparedStatement (Create/Read/Update/Delete).
 *
 * DEFINITION:
 *   Statement sends fixed SQL; PreparedStatement sends parameterized SQL with ?
 *   placeholders. ALWAYS prefer PreparedStatement: it prevents SQL injection and
 *   lets the DB cache the query plan.
 *
 * KEY POINTS:
 *   - executeUpdate() returns affected row count (INSERT/UPDATE/DELETE/DDL).
 *   - executeQuery() returns a ResultSet (SELECT).
 *   - ps.setInt/setString bind params (1-based index) — never concatenate input.
 *   - getGeneratedKeys() retrieves auto-increment ids.
 *
 * Runs a full demo IF an in-memory DB driver (H2/SQLite/HSQLDB) is on the
 * classpath; otherwise prints the SQL it would run. Enable with e.g.:
 *   java -cp ".;h2.jar" pkg11jdbc/jdbc2CrudStatements.java
 */
public class jdbc2CrudStatements {

    /** Tries common in-memory databases; returns a live Connection or null. */
    static Connection tryConnect() {
        String[] urls = {"jdbc:h2:mem:demo;DB_CLOSE_DELAY=-1",
                         "jdbc:sqlite::memory:",
                         "jdbc:hsqldb:mem:demo"};
        for (String url : urls) {
            try { return DriverManager.getConnection(url); }
            catch (SQLException ignored) { /* driver not present, try next */ }
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = tryConnect();
        if (conn == null) {
            System.out.println("No in-memory DB driver found. The SQL this demo runs:");
            System.out.println("""
                  CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), age INT);
                  INSERT INTO users (name, age) VALUES ('Ada', 36);   -- Create
                  SELECT id, name, age FROM users;                    -- Read
                  UPDATE users SET age = 37 WHERE name = 'Ada';       -- Update
                  DELETE FROM users WHERE name = 'Ada';               -- Delete""");
            System.out.println("\nAdd h2.jar/sqlite-jdbc.jar to the classpath to run it for real.");
            return;
        }

        try (conn) {
            // CREATE (DDL)
            try (Statement st = conn.createStatement()) {
                st.execute("CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT, " +
                           "name VARCHAR(50), age INT)");
            }

            // CREATE (rows) via PreparedStatement, fetching generated key
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO users (name, age) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, "Ada");
                ps.setInt(2, 36);
                int rows = ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) System.out.println("Inserted " + rows + " row, id=" + keys.getInt(1));
                }
            }

            // READ
            try (PreparedStatement ps = conn.prepareStatement("SELECT id, name, age FROM users");
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next())
                    System.out.printf("Read   : id=%d name=%s age=%d%n",
                            rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
            }

            // UPDATE
            try (PreparedStatement ps = conn.prepareStatement("UPDATE users SET age = ? WHERE name = ?")) {
                ps.setInt(1, 37);
                ps.setString(2, "Ada");
                System.out.println("Updated: " + ps.executeUpdate() + " row(s)");
            }

            // DELETE
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE name = ?")) {
                ps.setString(1, "Ada");
                System.out.println("Deleted: " + ps.executeUpdate() + " row(s)");
            }
        }
    }
}
