package DBAccess;

// Import Statements
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Division;
import utilities.DBConnection;
import utilities.DataProvider;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class DBDivisions {

    /**
     * This method selectAllDivisions and returns them as a DataProvider to use on modifyCustomer controller
     */
    public static void selectAllDivisions() {

        try {
            String sql = "SELECT * FROM first_level_divisions";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryID = rs.getInt("COUNTRY_ID");

                DataProvider.addDivision(new Division(divisionId,countryID,division));
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
