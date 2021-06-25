package utilities;

// Import statements
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {

    // Statement reference
    public static Statement statement;

    /**
     * This creates a static object
     * @param conn This is a parameter
     * @throws SQLException This is an exception
     */
    public static void setStatement(Connection conn) throws SQLException {
        statement = conn.createStatement();
    }

    /**
     * This is the return statement object
     * @return This is a return
     */
    public static Statement getStatement() {
        return statement;
    }

    /**
     * This is a prepared statement reference
     */
    private static PreparedStatement statement2;

    /**
     * This is a create prepared statement object
     * @param conn This is a parameter
     * @param sqlStatement This is a parameter
     * @throws SQLException This is an exception
     */
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        statement2 = conn.prepareStatement(sqlStatement);
    }

    /**
     * This is a return prepared statement object
     * @return This is a return statement
     */
    public static PreparedStatement getPreparedStatement() {
        return statement2;
    }
}
