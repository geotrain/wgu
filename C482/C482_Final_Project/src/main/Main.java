package main;

/**
 * @author Greg Westmoreland
 * C482 Class Project
 */

// Import statements
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

/**
 * This is the main class that begins the inventory management system application.
 */
public class Main extends Application {

    /**
     * This methods loads the main screen controller
     * @param primaryStage - This primary stage loads the main screen when the program is launched.
     * @throws Exception - This Exception occurs if an error occurs.
     */
    @Override public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 900, 525));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * This is the main method that starts the java application. It also contains the data arrays for the parts and
     * the product tables with data stored in an observable list array instead of a database. When the app is closed
     * any data additions or modifications is not saved.
     * @param args - This is the args that launches the program after the inventory is saved into the observable array list.
     */
    public static void main(String[] args) {

        InHouse IH1001 = new InHouse(1001, "Brakes", 39.95, 4, 3, 25, 1001);
        InHouse IN1002 = new InHouse(1002, "Wheel", 89.95, 7, 6, 25, 1002);
        InHouse IH1003 = new InHouse(1003, "Seat", 249.95, 5, 2, 25, 1003);

        Inventory.addPart(IH1001);
        Inventory.addPart(IN1002);
        Inventory.addPart(IH1003);

        Inventory.addPart(new InHouse(1004, "Alternator", 189.95, 15, 9, 25, 1004));
        Inventory.addPart(new InHouse(1005, "Water Pump", 129.95, 10, 8, 25, 1005));

        Outsourced OS2001 = new Outsourced(2006, "Floor Board Mats", 19.95, 29, 1, 50, "Rubber Stamp Inc.");
        Outsourced OS2002 = new Outsourced(2007, "Car Stereo", 399.95, 4, 1, 50, "Bose Inc.");
        Outsourced OS2003 = new Outsourced(2008, "Car Speakers", 179.95, 16, 1, 50, "Jensen Co.");

        Inventory.addPart(OS2001);
        Inventory.addPart(OS2002);
        Inventory.addPart(OS2003);

        Inventory.addPart(new Outsourced(2004, "LED Dash Lights", 24.95, 7, 1, 50, "Dashboard Lights Co."));
        Inventory.addPart(new Outsourced(2005, "Seat Covers", 29.95, 20, 1, 50, "We Got You Covered Inc."));

        Product prod1 = new Product(101, "Product One", 19.95, 10, 1, 75);
        prod1.addAssociatedPart(IH1001);
        prod1.addAssociatedPart(OS2001);
        Inventory.addProduct(prod1);

        Product prod2 = new Product(201, "Product Two", 29.95, 12, 1, 75);
        prod2.addAssociatedPart(IN1002);
        prod2.addAssociatedPart(OS2002);
        Inventory.addProduct(prod2);

        Product prod3 = new Product(301, "Product Three", 39.95, 14, 1, 75);
        prod3.addAssociatedPart(IH1003);
        prod3.addAssociatedPart(OS2003);
        Inventory.addProduct(prod3);

        Product prod4 = new Product(401, "Product Four", 49.95, 16, 1, 75);
        Inventory.addProduct(prod4);

        Inventory.addProduct(new Product(501, "Product Five", 59.95, 18, 1, 75));

        // Launch args which loads the program
        launch(args);
    }
}

