package pkg11jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * jdbc4TransactionsAndBatch.java
 * ------------------------------
 * Transactions (commit/rollback) and batch updates for correctness & speed.
 *
 * DEFINITION:
 *   A transaction groups statements into an all-or-nothing unit (ACID). Batching
 *   sends many statements to the DB in one round trip for throughput.
 *
 * KEY POINTS:
 *   - setAutoCommit(false) starts manual transaction control.
 *   - commit() makes changes permanent; rollback() undoes them on error.
 *   - Savepoints allow partial rollback within a transaction.
 *   - addBatch()/executeBatch() drastically reduce per-statement overhead.
 *
 * Runs for real with an in-memory DB driver; otherwise prints guidance.
 */
public class jdbc4TransactionsAndBatch {

    static Connection tryConnect() {
        for (String url : new String[]{"jdbc:h2:mem:demo;DB_CLOSE_DELAY=-1",
                                       "jdbc:sqlite::memory:", "jdbc:hsqldb:mem:demo"}) {
            try { return DriverManager.getConnection(url); } catch (SQLException ignored) {}
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = tryConnect();
        if (conn == null) {
            System.out.println("No in-memory DB driver found. This demo would:");
            System.out.println("  1. setAutoCommit(false)");
            System.out.println("  2. batch-insert 5 rows, then commit()");
            System.out.println("  3. attempt a bad transfer and rollback() to keep balances consistent");
            System.out.println("Add h2.jar to the classpath to run it for real.");
            return;
        }

        try (conn) {
            try (Statement st = conn.createStatement()) {
                st.execute("CREATE TABLE account (id INT PRIMARY KEY, balance INT)");
            }

            // BATCH insert inside a transaction
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO account VALUES (?, ?)")) {
                for (int i = 1; i <= 5; i++) {
                    ps.setInt(1, i);
                    ps.setInt(2, 100);
                    ps.addBatch();                 // queue, don't send yet
                }
                int[] counts = ps.executeBatch();  // one round trip
                conn.commit();
                System.out.println("Batch inserted " + counts.length + " rows, committed.");
            }

            // A transfer that fails — demonstrate rollback keeps data consistent
            try {
                try (PreparedStatement debit  = conn.prepareStatement(
                         "UPDATE account SET balance = balance - 50 WHERE id = 1");
                     PreparedStatement credit = conn.prepareStatement(
                         "UPDATE account SET balance = balance + 50 WHERE id = 999")) { // no such row
                    debit.executeUpdate();
                    int credited = credit.executeUpdate();
                    if (credited == 0) throw new SQLException("destination account missing");
                    conn.commit();
                }
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Transfer failed -> rolled back: " + e.getMessage());
            }

            // Verify account 1 still has 100 (debit undone)
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery("SELECT balance FROM account WHERE id = 1")) {
                if (rs.next()) System.out.println("Account 1 balance after rollback: " + rs.getInt(1));
            }
        }
    }
}
