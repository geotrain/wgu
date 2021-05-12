package utilities;

// Import statements
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {

    // Statement reference
    public static Statement statement;

    // Create Statement Object
    public static void setStatement(Connection conn) throws SQLException {
        statement = conn.createStatement();
    }

    // Return statement object
    public static Statement getStatement() {
        return statement;
    }

    // PreparedStatement reference
    private static PreparedStatement statement2;

    // Create preparedStatement Object
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        statement2 = conn.prepareStatement(sqlStatement);
    }

    // Return preparedStatement object
    public static PreparedStatement getPreparedStatement() {
        return statement2;
    }

}
