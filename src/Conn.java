import java.sql.*;

public class Conn {
    public Connection c;
    public Statement s;

    public Conn() {
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB
            c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotelmanagementsystem?useSSL=false&serverTimezone=UTC",
                "root",
                "23G10s04v$$"  // your MySQL password
            );

            // Create statement
            s = c.createStatement();

        } catch (Exception e) {
            System.out.println("❌ JDBC Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
