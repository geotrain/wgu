package controller;

/**
 * @author Greg Westmoreland
 * C482 Class Project
 */

// Import statements
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.util.Optional;

/**
 * This class is where a part can be modified and resaved back to the inventory list.
 */
public class ModifyPartController {

    // FX IDs for HBox
    @FXML private HBox topMenu;

    // FX IDs for Radio Buttons
    @FXML private RadioButton inHouseRadio;
    @FXML private RadioButton outSourceRadio;
    @FXML private ToggleGroup inHouseOutSourceToggleGroupinHouseOutSourceToggleGroup;

    // FX IDs for Labels
    @FXML private Label label;
    @FXML private Label errorLabel;

    // FX IDs for Text Fields
    @FXML private TextField id;
    @FXML private TextField name;
    @FXML private TextField price;
    @FXML private TextField count;
    @FXML private TextField min;
    @FXML private TextField machineIdOrCompanyName;
    @FXML private TextField max;

    // FX IDs for Buttons
    @FXML private Button modifyPartSaveButton;
    @FXML private Button cancelButton;

    // Declare machineIdCompanyname object for Modify Part Method
    private Part machineIdCompanyName;

    /**
     * Initialize stage and scene variables for app navigation
     */
    Stage stage;
    Parent scene;

    @FXML void idFieldNotAbleToEdit(MouseEvent event) {
        errorLabel.setText("You cannot edit the ID field.");
    }

    /**
     * This method assigns label machine ID if the in house radio button is selected.
     * @param event - This event handler displays Machine ID label if the in-house radio button is selected.
     */
    @FXML void inHouseButtonSelected(ActionEvent event) {
        if(inHouseRadio.isSelected()) {
            label.setText("  Machine ID");
        }
    }

    /**
     * This method assigns label Company Name if outsourced radio button is selected.
     * @param event - This event handler displays Company Name if the outsourced radio button is selected.
     */
    @FXML void outsourcedRadioButtonSelected(ActionEvent event) {
        if(outSourceRadio.isSelected()) {
            label.setText("  Company Name");
        }
    }

    /**
     * This initializes selectedPartResult variable to be used for modifying a part
     */
    public static String selectedPartResult;

    /**
     * This method receives product data from the main screen controller and populates the text fields to be modified
     * @param part - This part is used to set the part data to the modify screen.
     */
    public void setDataPart(Part part) {
        machineIdCompanyName = part;
        this.id.setText(String.valueOf(machineIdCompanyName.getId()));
        this.name.setText(String.valueOf(machineIdCompanyName.getName()));
        this.price.setText(String.valueOf(machineIdCompanyName.getPrice()));
        this.count.setText(String.valueOf(machineIdCompanyName.getStock()));
        this.min.setText(String.valueOf(machineIdCompanyName.getMin()));
        this.max.setText(String.valueOf(machineIdCompanyName.getMax()));
        if(machineIdCompanyName instanceof InHouse) {
            inHouseRadio.setSelected(true);
            label.setText("Machine ID");
            machineIdOrCompanyName.setText(String.valueOf(((InHouse)machineIdCompanyName).getMachineId()));
            System.out.println(machineIdCompanyName);
        }
        else if(machineIdCompanyName instanceof Outsourced) {
            outSourceRadio.setSelected(true);
            label.setText("Company Name");
            machineIdOrCompanyName.setText(String.valueOf(((Outsourced)machineIdCompanyName).getCompanyName()));
        }
    }

    /**
     * This method saves the modified data and saves it back to the part table on the main screen controller.
     * @param event - This event handler saves the modified data for a part and returns to the main screen.
     * @throws IOException - This IO Exception occurs if an error occurs.
     */
    @FXML void modifyButton(ActionEvent event) throws IOException {
        // Error Message if in-house or outsourced radio buttons are not selected prior to pushing the save button
        if (!inHouseRadio.isSelected() && !outSourceRadio.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Select In-House or Radio Button Before Proceeding!");
            alert.setContentText("You must either the In-House or the Outsourced Radio Button!          Empty " +
                    "Value Checks Are Done After You Select A Radio Button");
            alert.showAndWait();
            return;
        }

        // Actions if in-house radio button is selected.
        if (inHouseRadio.isSelected()) {
            Part machineIdNumber = new InHouse(Integer.parseInt(id.getText()), "", 0.00, 0, 0, 0, 0);

            // Verify name text field is not blank
            if (name.getText().isEmpty()) {
                errorLabel.setText("The \"name\" text field cannot be blank.");
                return;
            } else {
                machineIdNumber.setName(name.getText());
            }

            // Verify price text field is not blank
            if (price.getText().isEmpty()) {
                errorLabel.setText("The \"price\" text field cannot be blank.");
                return;
                // Verify price text field is not a string
            } else if (!isPriceNumeric(price.getText())) {
                return;
            } else {
                machineIdNumber.setPrice(Double.parseDouble(price.getText()));
            }

            // Verify count text field is not blank
            if (count.getText().isEmpty()) {
                errorLabel.setText("The \"count\" text field cannot be blank.");
                return;
                // Verify the count text field is not a double or floating point
            } else if (!tryIntegerValue(count.getText())) {
                errorLabel.setText("Inventory number must be an integer value.");
                return;
            } else {
                machineIdNumber.setStock(Integer.parseInt(count.getText()));
            }

            // Verify min text field is not blank
            if (min.getText().isEmpty()) {
                errorLabel.setText("The \"min\" text field cannot be blank.");
                return;
                // Verify the min text field is not a double or floating point
            } else if (!tryIntegerValue(min.getText())) {
                errorLabel.setText("The min number must be an integer value.");
                return;
            } else {
                machineIdNumber.setMin(Integer.parseInt(min.getText()));
            }

            // Verify max text field is not blank
            if (max.getText().isEmpty()) {
                errorLabel.setText("The \"max\" text field cannot be blank.");
                return;
                // Verify the max text field is not a double or floating point
            } else if (!tryIntegerValue(max.getText())) {
                errorLabel.setText("The max number must be an integer value.");
                return;
            } else {
                machineIdNumber.setMax(Integer.parseInt(max.getText()));
            }

            // Verify the Machine ID text field is not blank
            if(machineIdOrCompanyName.getText().isEmpty()) {
                errorLabel.setText("The \"Machine ID\" text field cannot be blank.");
                return;
            // Verify Machine ID is not a string when modifying outsourced to in-house part
            } else if (!isStringAnInteger(machineIdOrCompanyName.getText())) {
                return;
            }

            // Check count text field, must be greater than min and smaller than max
            if (Integer.parseInt(count.getText()) < Integer.parseInt(min.getText())) {
                errorLabel.setText("Inventory must be larger than or equal to the min.");
                return;
                // Verify the count text field must be smaller than or equal to the max text field
            } else if (Integer.parseInt(count.getText()) > Integer.parseInt(max.getText())) {
                errorLabel.setText("Inventory must be smaller than or equal to the max.");
                return;
                // Verify the min text field is smaller than the max text field
            } else if (Integer.parseInt(min.getText()) > Integer.parseInt(max.getText())) {
                errorLabel.setText("Min must be smaller than the max.");
                return;
            }

            // Saving modified in house part to inventory
            errorLabel.setText("");
            System.out.println(machineIdNumber);
            System.out.println("Save Button Pushed");
            ((InHouse) machineIdNumber).setMachineId(Integer.parseInt(machineIdOrCompanyName.getText()));
            System.out.println(Integer.parseInt(machineIdOrCompanyName.getText()));
            Inventory.updatePart(Integer.parseInt(machineIdOrCompanyName.getText()), machineIdNumber);
        }

            // Actions if outsources radio button is selected.
            if (outSourceRadio.isSelected()) {
                Part companyName = new Outsourced(Integer.parseInt(id.getText()), "", 0.00, 0, 0, 0, "");

                // Verify name text field is not blank
                if (name.getText().isEmpty()) {
                    errorLabel.setText("The \"name\" text field cannot be blank.");
                    return;
                } else {
                    companyName.setName(name.getText());
                }

                // Verify price text field is not blank
                if (price.getText().isEmpty()) {
                    errorLabel.setText("The \"price\" text field cannot be blank.");
                    return;
                    // Verify price text field is not a string
                } else if (!isPriceNumeric(price.getText())) {
                    return;
                } else {
                    companyName.setPrice(Double.parseDouble(price.getText()));
                }

                // Verify count text field is not blank
                if (count.getText().isEmpty()) {
                    errorLabel.setText("The \"count\" text field cannot be blank.");
                    return;
                    // Verify the count text field is not a double or floating point
                } else if ((Integer.parseInt(count.getText()) % 1) != 0) {
                    errorLabel.setText("Inventory number must be an integer value.");
                    return;
                } else {
                    companyName.setStock(Integer.parseInt(count.getText()));
                }

                // Verify min text field is not blank
                if (min.getText().isEmpty()) {
                    errorLabel.setText("The \"min\" text field cannot be blank.");
                    return;
                    // Verify the min text field is not a double or floating point
                } else if ((Integer.parseInt(min.getText()) % 1) != 0) {
                    errorLabel.setText("The min number must be an integer value.");
                    return;
                } else {
                    companyName.setMin(Integer.parseInt(min.getText()));
                }

                // Verify max text field is not blank
                if (max.getText().isEmpty()) {
                    errorLabel.setText("The \"max\" text field cannot be blank.");
                    return;
                    // Verify the max text field is not a double or floating point
                } else if ((Integer.parseInt(max.getText()) % 1) != 0) {
                    errorLabel.setText("The max number must be an integer value.");
                    return;
                } else {
                    companyName.setMax(Integer.parseInt(max.getText()));
                }

                // Verify the Company Name text field is not blank
                if(machineIdOrCompanyName.getText().isEmpty()) {
                    errorLabel.setText("The \"Company Name\" text field cannot be blank.");
                    return;
                }

                // Check count text field, must be greater than min and smaller than max
                if (Integer.parseInt(count.getText()) < Integer.parseInt(min.getText())) {
                    errorLabel.setText("Inventory must be larger than or equal to the min.");
                    return;
                    // Verify the count text field must be smaller than or equal to the max text field
                } else if (Integer.parseInt(count.getText()) > Integer.parseInt(max.getText())) {
                    errorLabel.setText("Inventory must be smaller than or equal to the max.");
                    return;
                    // Verify the min text field is smaller than the max text field
                } else if (Integer.parseInt(min.getText()) > Integer.parseInt(max.getText())) {
                    errorLabel.setText("Min must be smaller than the max.");
                    return;
                }

                // Saving modified outsourced part to inventory
                errorLabel.setText("");
                System.out.println(companyName);
                System.out.println("Save Button Pushed");
                ((Outsourced) companyName).setCompanyName(machineIdOrCompanyName.getText());
                System.out.println(machineIdOrCompanyName);
                Inventory.updatePart(0, companyName);
            }

            // Return to main screen after save button is selected.
            System.out.println("Save Button Pushed");
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
            stage.setScene(new Scene(scene, 900, 525));
            stage.setTitle("Inventory Management System");
            stage.setResizable(false);
            stage.show();
    }

    /**
     * This method returns to the main screen Inventory Management System.
     * @param event - This event handler cancels all actions and returns to the main screen.
     * @throws IOException - This IO Exception occurs if an error occurs.
     */
    @FXML void cancelButton(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel and Return To Main Screen");
        alert.setHeaderText("Are you sure you want to return to the main screen?");
        alert.setContentText("Click OK to continue.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.out.println("Cancel Button Pushed");
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
            stage.setScene(new Scene(scene, 900, 525));
            stage.setTitle("Inventory Management System");
            stage.setResizable(false);
            stage.show();
        } else {
            errorLabel.setText("You have returned to the modify part screen.");
        }
    }

    /**
     * This method checks to see if changing an outsourced part to an in-house product has an integer value instead of a string.
     * @param s - This is used to verify that when a user changes from outsourced to in-house string the value is not a string.
     * @return - This returns true if it is an integer and false if it is a string.
     */
    public boolean isStringAnInteger(String s)
    {
        try {
            Integer.parseInt(s);
            errorLabel.setText("");
            return true;
        } catch (NumberFormatException ex) {
            errorLabel.setText("Change \"Machine ID\" text field from a string to an integer.");
            System.out.println("isStringAnInteger returns false");
            return false;
        }
    }

    /**
     * Method checks to see if price text field value entered is a numeric value only.
     * @param strNum - This string is used to verify the price is not a string value for validation purposes.
     * @return - This returns true if it is a double and false if it is a string.
     */
    public boolean isPriceNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
            errorLabel.setText("");
            return true;
        } catch (NumberFormatException nfe) {
            errorLabel.setText("Change \"Price\" text field from a string to an double.");
            System.out.println("isPriceNumeric returns false");
            return false;
        }
    }

    /**
     * This method checks if the integer values for inventory, min, and max text fields are integers and not doubles or floats.
     * @param strNum - This string is used to verify the value is an integer and not a double or a float for validation purposes.
     * @return - This returns true if it is an integer and false if it is a double or floating point.
     */
    public boolean tryIntegerValue(String strNum) {
        try {
            Integer.parseInt(strNum);
            errorLabel.setText("");
            return true;
        } catch (NumberFormatException e) {
            errorLabel.setText("Enter an integer value instead of a number with a decimal.");
            System.out.println("tryIntegerValue returns false");
            return false;
        }
    }
}
