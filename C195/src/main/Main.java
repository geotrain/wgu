package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.DatabaseConnection;
import javafx.application.Application;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        primaryStage.setTitle("Appointment User Login");
        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        DatabaseConnection.startConnection(); // This will start database connection when launching the app
        DBAccess.DBCountries.checkDatabaseConversion();
        launch(args);
        DatabaseConnection.closeConnection(); // This will close database connection when exiting the app
    }

}
