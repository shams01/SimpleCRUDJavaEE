package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try{
                Class.forName("org.postgresql.Driver");

                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/userDB",
                        "postgres", "postgres");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
}
