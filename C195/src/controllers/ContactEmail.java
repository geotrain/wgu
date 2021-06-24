package controllers;

import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Contacts;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContactEmail implements Initializable {

    @FXML private Label contactsEmailListLabel;

    @FXML private Button closeButton;

    @FXML private TableView<Contacts> contactEmailTableView;
    @FXML private TableColumn<Contacts, String> contactNameColumn;
    @FXML private TableColumn<Contacts, String> contactEmailColumn;

    /**
     * This method will close the add appointment controller without saving any data and returns to the main controller
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
     * The initialize method will load the ContactEmailTableVIew
     * @param url
     * @param resourceBundle
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

        // Populates and initializes the contacts email list table view
        contactNameColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        contactEmailColumn.setCellValueFactory(new PropertyValueFactory<>("contactEmail"));
        contactEmailTableView.setItems(DBContacts.getContactsEmailList());
    }
}
