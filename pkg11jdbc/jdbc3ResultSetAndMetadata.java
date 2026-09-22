package pkg11jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * jdbc3ResultSetAndMetadata.java
 * ------------------------------
 * Reading rows from a ResultSet and discovering columns via ResultSetMetaData.
 *
 * DEFINITION:
 *   A ResultSet is a forward cursor over query results. ResultSetMetaData
 *   describes the shape of those results (column names, types, counts) — useful
 *   for generic tools that don't know the schema ahead of time.
 *
 * KEY POINTS:
 *   - rs.next() advances the cursor; returns false past the last row.
 *   - Read columns by 1-based index OR by name; getObject() is type-generic.
 *   - getMetaData() gives column count, labels, and SQL type names.
 *   - Watch for NULLs: use wasNull() after a primitive getXxx() if it matters.
 *
 * Runs for real with an in-memory DB driver; otherwise prints guidance.
 */
public class jdbc3ResultSetAndMetadata {

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
            System.out.println("No in-memory DB driver found.");
            System.out.println("This demo would SELECT rows, then print each column's");
            System.out.println("name and type using ResultSetMetaData. Add h2.jar to run it.");
            return;
        }

        try (conn; Statement st = conn.createStatement()) {
            st.execute("CREATE TABLE product (id INT, name VARCHAR(30), price DECIMAL(8,2))");
            st.execute("INSERT INTO product VALUES (1,'Keyboard',49.99),(2,'Mouse',19.50)");

            try (ResultSet rs = st.executeQuery("SELECT * FROM product ORDER BY id")) {
                ResultSetMetaData md = rs.getMetaData();
                int cols = md.getColumnCount();

                // Describe the columns (metadata)
                System.out.println("Columns (" + cols + "):");
                for (int i = 1; i <= cols; i++)
                    System.out.printf("  %d. %-8s %s%n", i, md.getColumnName(i), md.getColumnTypeName(i));

                // Iterate the rows generically
                System.out.println("\nRows:");
                while (rs.next()) {
                    StringBuilder row = new StringBuilder("  ");
                    for (int i = 1; i <= cols; i++)
                        row.append(md.getColumnName(i)).append('=').append(rs.getObject(i)).append("  ");
                    System.out.println(row.toString().stripTrailing());
                }
            }
        }
    }
}
