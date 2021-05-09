package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//3.227.166.251/WJ04tpv";

    // Concatenate jdbc://mysql://3.227.166.251/WJ04tpv into one variable
    private static final String jdbcUrl = protocol + vendorName + ipAddress;

    // Driver And Connection Interface References
    private static final String mysqlJdbcDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    // Database username and password to uCertify database
    private static final String userName = "U04tpv";
    private static final String password = "53688343157";

    public static Connection startConnection()
    {
        try {
            Class.forName(mysqlJdbcDriver);
            conn = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("Connection to database was successful.");
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeConnection()
    {
        try {
            conn.close();
            System.out.println("Connection to database was closed.");
        }
        catch(SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}


