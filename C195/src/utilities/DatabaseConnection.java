package utilities;

// Import statements
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Declare MySQL connection string variables
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

    /**
     * This method starts the connection to the uCertify database and is called in the Main.java before launch(args)
     * @return conn
     */
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

    /**
     * This method closes the connection to the uCertify database and is called in the Main.java when exiting the program
     */
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

    /**
     * This method gets the connection from the uCertify database when making sql statements throughout the program
     * @return
     */
    public static Connection getConnection() {
        return conn;
    }
}


