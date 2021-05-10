package utilities;

// Import statements
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {

    // Statement reference
    private static Statement statement;

    // Create Statement Object
    public static void setStatement(Connection conn) throws SQLException {
        statement = conn.createStatement();
    }

    // Return statement object
    public static Statement getStatement() {
        return statement;
    }


}
