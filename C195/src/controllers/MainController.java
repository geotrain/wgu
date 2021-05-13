package controllers;

import DBAccess.DBAppointments;
import DBAccess.DBCountries;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Appointments;
import models.Countries;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public Label TheLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
    }

    public void onCountriesButtonAction(ActionEvent actionEvent) {
        TheLabel.setText("See Terminal For Countries Table Records.");

        ObservableList<Countries> countryList = DBCountries.getAllCountries();
        for(Countries C : countryList)
            System.out.println(
                    "Country Id: " + C.getId() +
                    " Name: " + C.getName() +
                    " Create Date: " + C.getCreateDate() + " " + C.getCreateDateTime() +
                    " Created By: " +C.getCreatedBy() +
                    " Last Update: " + C.getLastUpdateDate() + " " + C.getLastUpdate() +
                    " Last Updated By: " + C.getLastUpdatedBy() + "\n"
            );
    }

    public void onAppointmentsButtonAction(ActionEvent actionEvent) {
        TheLabel.setText("See Terminal For Appointments Table Records.");

        ObservableList<Appointments> apoointmentsList = DBAppointments.getAllAppointments();
        for(Appointments A : apoointmentsList)
            System.out.println(
                    "Appointment ID: " + A.getId() +
                    " Title: " + A.getTitle() +
                    " Description " + A.getDescription() +
                    " Location: " + A.getLocation() +
                    " Type: " + A.getType() +
                    " Start: " + A.getStart() +
                    " End: " + A.getEnd() +
                    " Create Date: " + A.getCreateDate() +
                    " Created By: " + A.setCreatedBy() +
                    " Last Update: " + A.getLastUpdate() +
                    " Last Updated By: " + A.getLastUpdatedBy() +
                    " Customer ID: " + A.getCustomerId() +
                    " User ID: " + A.getUserId() +
                    " Contact ID: " + A.getContactId() + "\n"
            );
    }
}