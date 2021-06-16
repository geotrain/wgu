package DBAccess;

// Import statements
import models.Countries;
import utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DataProvider;

import java.sql.*;

public class DBCountries {

    /**
     * This ObservableList returns all the countries from the countries table the Country ID and Country Name. This is
     * used in conjunction with the models/Countries.java file that contains the getId and getName methods
     *
     * @param <Countries>
     * @return
     */

    public static void selectAllCountries() {
        try {
            String sql = "SELECT * FROM countries";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");

                DataProvider.addCountry(new Countries(countryId, countryName));
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method check's the dataabse connection with the create_date and then outputs it to the screen
     * @throws SQLException
     */
    public static void checkDatabaseConversion() throws SQLException {
        System.out.println("CREATE DATE TEST");
        String sql = "select Create_Date from countries";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Timestamp ts = rs.getTimestamp("Create_Date");
                System.out.println("CD:" + ts.toLocalDateTime().toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}