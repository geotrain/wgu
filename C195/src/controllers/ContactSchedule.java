package controllers;
// Import Statments
import DBAccess.DBContacts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.ToggleGroup;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContactSchedule implements Initializable {

    // FX ID's Labels
    @FXML private Label contactsEmailListLabel;
    @FXML private Label chooseContactLabel;

    // FX ID's Buttons'
    @FXML private Button closeButton;

    // FX ID's Table View and Table Columns
    @FXML private TableView<?> contactEmailTableView;
    @FXML private TableColumn<?, ?> appointmentIdColumn;
    @FXML private TableColumn<?, ?> titleColumn;
    @FXML private TableColumn<?, ?> typeColumn;
    @FXML private TableColumn<?, ?> descriptionColumn;
    @FXML private TableColumn<?, ?> startColumn;
    @FXML private TableColumn<?, ?> endColumn;
    @FXML private TableColumn<?, ?> customerIdColumn;

    // FX ID's Radio Buttons
    @FXML private RadioButton contactOneRadioButton;
    @FXML private RadioButton contactTwoRadioButton;
    @FXML private RadioButton contactThreeRadioButton;

    @FXML
    private ToggleGroup contactToggleGroup;

    /**
     * This method will close the Contact Email controller
     * @param actionEvent
     * @throws IOException
     */
    public void close(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 1060, 875);
        stage.setTitle("Welcome To Schedule Manager v 1.0");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This initialize method will generate the Contact Schedule Table View Upon Radio Button Selection
     * @param url
     * @param resourceBundle
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

        // Populates and initializes the contacts email list table view
        //contactNameColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        //contactEmailColumn.setCellValueFactory(new PropertyValueFactory<>("contactEmail"));
        //contactEmailTableView.setItems(DBContacts.getContactsEmailList());
    }

    public void contactOneSelection(javafx.event.ActionEvent actionEvent) {
    }

    public void contactTwoSelection(javafx.event.ActionEvent actionEvent) {
    }

    public void contactThreeSelection(javafx.event.ActionEvent actionEvent) {
    }
}
