import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Exact values from your Railway 'Variables' and 'Public URL'
    private static final String HOST = "trolley.proxy.rlwy.net"; 
    private static final String PORT = "48755";
    private static final String DB_NAME = "railway";
    private static final String USER = "root";
    private static final String PASS = "oImItElZhxmfuSzbctSyUVpebiWEHicg";

    // Format: jdbc:mysql://host:port/database
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;

    public static Connection getConnection() {
        try {
            // Loading the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Attempting to connect to your Railway database
            return DriverManager.getConnection(URL, USER, PASS);
            
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: MySQL JDBC Driver not found. Ensure the JAR is in Referenced Libraries.");
            return null;
        } catch (SQLException e) {
            System.err.println("❌ Error: Could not connect to Railway. Check your internet or credentials.");
            e.printStackTrace();
            return null;
        }
    }
}