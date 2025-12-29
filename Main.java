import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        // These credentials match your Railway setup
        // Added SSL and Public Key retrieval flags to help bypass connection blocks
        String url = "jdbc:mysql://trolley.proxy.rlway.net:48755/railway?useSSL=true&trustServerCertificate=true&allowPublicKeyRetrieval=true";
        String user = "root";
        String pass = "oImItElZhxmfuSzbctSyUVpebiWEHicg";

        System.out.println("Connecting to Railway Cloud Database...");

        // try-with-resources ensures the connection closes automatically
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            System.out.println("? Success! Connection established.");
            System.out.println("--------------------------------------------------");

            // 1. Create a Statement object to send SQL to the database
            Statement stmt = conn.createStatement();

            // 2. Execute a SELECT query to see your books
            String sql = "SELECT id, title, author, status FROM books";
            ResultSet rs = stmt.executeQuery(sql);

            // 3. Iterate through the results and print them
            System.out.println("CURRENT INVENTORY:");
            boolean dataFound = false;
            while (rs.next()) {
                dataFound = true;
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String status = rs.getString("status");

                System.out.printf("ID: %-5d | Title: %-20s | Author: %-15s | Status: %-10s %n", 
                                  id, title, author, status);
            }

            if (!dataFound) {
                System.out.println("The table is empty. Add a row in the Railway dashboard first!");
            }

            System.out.println("--------------------------------------------------");

        } catch (SQLException e) {
            // This will catch the 'UnknownHostException' if your DNS is still blocked
            System.err.println("!!! Database Error !!!");
            System.err.println("Message: " + e.getMessage());
            System.err.println("\nTIP: If you see 'UnknownHost', check your internet or use a VPN/Hotspot.");
        }
    }
}