package aed.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initializeDatabase(String username, String password) {
        String url = "jdbc:mariadb://localhost:3306/";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS karaoke_db");
            statement.executeUpdate("USE karaoke_db");

            // Read and execute the SQL script
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    DatabaseInitializer.class.getResourceAsStream("/init.sql")))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    statement.executeUpdate(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}