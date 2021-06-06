package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCustomer {

    ObservableList<String> activeList = FXCollections.observableArrayList("True");
    ObservableList<String> countriesList = FXCollections.observableArrayList("U.S", "UK", "Canada");

    @FXML private Label customerIdLabel;
    @FXML private Label customerNameLabel;
    @FXML private Label address1Label;
    @FXML private Label address2Label;
    @FXML private Label cityLabel;
    @FXML private Label countryLabel;
    @FXML private Label zipCodeLabel;
    @FXML private Label phoneNumberLabel;
    @FXML private Label activeLabel;

    @FXML private TextField customerIdTextField;
    @FXML private TextField customerNameTextField;
    @FXML private TextField address1TextField;
    @FXML private TextField address2TextField;
    @FXML private TextField countryTextField;
    @FXML private TextField zipCodeTextField;
    @FXML private TextField phoneNumberTextField;

    @FXML private ChoiceBox<String> countriesChoiceBox;
    @FXML private ChoiceBox<String> activeChoiceBox;

    @FXML private Button saveButton;
    @FXML private Button closeButton;

    @FXML private Label addCustomerLabel;

    @FXML
    void close(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 1060, 875);
        stage.setTitle("Welcome To Schedule Manager v 1.0");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML private void initialize() {
        countriesChoiceBox.setValue("U.S");
        countriesChoiceBox.setItems(countriesList);

        activeChoiceBox.setValue("True");
        activeChoiceBox.setItems(activeList);

    }
}
