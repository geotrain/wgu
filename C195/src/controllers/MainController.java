package controllers;

import DBAccess.DBCountries;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
        System.out.println("Button Clicked");
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
}