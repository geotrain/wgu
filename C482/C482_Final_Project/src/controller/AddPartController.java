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
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import javafx.scene.layout.HBox;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

/**
 * This class is the add part controller screen where users add a new part to the inventory.
 */
public class AddPartController {

    // FX Ids For Horizontal Boxes
    @FXML private HBox topMenu;

    // FX Ids for Radio Buttons@FXML
    private RadioButton inHouseRadio;
    @FXML private RadioButton outSourceRadio;
    @FXML private ToggleGroup inHouseOutSourceToggleGroup;

    // FX IDs for Labels
    @FXML private Label label;
    @FXML private Label errorLabel;

    // FX Ids For Text Fields
    @FXML private TextField id;
    @FXML private TextField name;
    @FXML private TextField price;
    @FXML private TextField count;
    @FXML private TextField min;
    @FXML private TextField max;
    @FXML private TextField machineIdOrCompanyName;

    // FX Ids For Buttons
    @FXML private Button modifyPartSaveButton;
    @FXML private Button cancelButton;

    // ID Label Not Able To Edit
    @FXML void idFieldNotAbleToEdit(MouseEvent event) {
        errorLabel.setText("Cannot edit the ID field. Select Radio Button To Populate.");
    }

    /**
     * Initialize stage and scene variables for app navigation
     */
    Stage stage;
    Parent scene;

    /**
     * Initialize variable Random to hold an automated partId that is called in the saveButton method
     * Initialize variable result to hold the final result from the random() function called in the saveButton method
     */
    private static Random random = new Random();
    private static int result;

    /**
     * This method displays Machine ID if the in-house radio button is selected.
     * This method also creates a partID with the random() method.
     * @param event - This event handler labels the text field Machine ID if selected.
     */
    @FXML void inHouseRadioButtonSelected(ActionEvent event) {
        if (inHouseRadio.isSelected()) {
            label.setText("  Machine ID");
        }
        // Auto-Generate part id with random() method.
        int nextId = random.nextInt(100) + 1;
        if (nextId == 1001 || nextId == 1002 || nextId == 1003 || nextId == 1004 || nextId == 1005 || nextId == 2004
                || nextId == 2005 || nextId == 2006 || nextId == 2007 || nextId == 2008) {
            errorLabel.setText("You have a duplicate part id. Return to main screen and try again");
            return;
        } else {
            result += nextId;
            System.out.println("New Part Id Generated Is: " + result);
        }
        id.setText(Integer.toString(result));
    }

    /**
     * This method displayed the Company name if the outsources radio button is selected.
     * @param event - This event handler labels the text field Company Name if selected.
     */
    @FXML void outsourcedRadioButtonSelected(ActionEvent event) {
        if (outSourceRadio.isSelected()) {
            label.setText("  Company Name");
        }
        // Auto-Generate part id with random() method.
        int nextId = random.nextInt(100) + 1;
        if (nextId == 1001 || nextId == 1002 || nextId == 1003 || nextId == 1004 || nextId == 1005 || nextId == 2004
                || nextId == 2005 || nextId == 2006 || nextId == 2007 || nextId == 2008) {
            errorLabel.setText("You have a duplicate part id. Return to main screen and try again");
            return;
        } else {
            result += nextId;
            System.out.println("New Part Id Generated Is: " + result);
        }
        id.setText(Integer.toString(result));
    }

    /**
     * This method saves the part to the table view on the main screen controller.
     * @param event - This event handler saves the changes to a new part added by the user.
     * @throws IOException - This throws an IOException if an error occurs.
     */
    @FXML void saveButton(ActionEvent event) throws IOException {
        // Error Message if in-house or outsourced radio buttons are not selected prior to pushing the save button
       if (!inHouseRadio.isSelected() && !outSourceRadio.isSelected() && name.getText().isEmpty()) {
            errorLabel.setText("The \"name\" text field cannot be blank.");
            return;
        } else if (!inHouseRadio.isSelected() && !outSourceRadio.isSelected() && count.getText().isEmpty()) {
            errorLabel.setText("The \"inventory\" text field cannot be blank.");
            return;
        } else if (!inHouseRadio.isSelected() && !outSourceRadio.isSelected() && price.getText().isEmpty()) {
            errorLabel.setText("The \"price\" text field cannot be blank.");
            return;
        } else if (!inHouseRadio.isSelected() && !outSourceRadio.isSelected() && max.getText().isEmpty()) {
            errorLabel.setText("The \"max\" text field cannot be blank.");
            return;
        } else if (!inHouseRadio.isSelected() && !outSourceRadio.isSelected() && min.getText().isEmpty()) {
            errorLabel.setText("The \"min\" text field cannot be blank.");
            return;
       } else if (!inHouseRadio.isSelected() && !outSourceRadio.isSelected() && machineIdOrCompanyName.getText().isEmpty()) {
           errorLabel.setText("The \"Machine ID \\ Company Name\" cannot be blank.");
           return;
        } else if (!inHouseRadio.isSelected() && !outSourceRadio.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Select In-House or Radio Button Before Proceeding!");
            alert.setContentText("You must either the In-House or the Outsourced Radio Button!");
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
                // Verify the min text field is smaller than the max text field
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
                return;
            } else {
                machineIdNumber.setMax(Integer.parseInt(max.getText()));
            }

            // Verify the Machine ID text field is not blank
            if(machineIdOrCompanyName.getText().isEmpty()) {
                errorLabel.setText("The \"Machine ID\" text field cannot be blank.");
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

            // Saving new in house part to inventory
            errorLabel.setText("");
            System.out.println(machineIdNumber);
            System.out.println("Save Button Pushed");
            ((InHouse) machineIdNumber).setMachineId(Integer.parseInt(machineIdOrCompanyName.getText()));
            Inventory.addPart(machineIdNumber);
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
            } else if (!tryIntegerValue(count.getText())) {
                errorLabel.setText("The inventory number must be an integer value.");
                return;
            } else {
                companyName.setStock(Integer.parseInt(count.getText()));
            }

            // Verify min text field is not blank
            if (min.getText().isEmpty()) {
                errorLabel.setText("The \"min\" text field cannot be blank.");
                return;
                // Verify the min text field is smaller than the max text field
            } else if (!tryIntegerValue(min.getText())) {
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
            } else if (!tryIntegerValue(max.getText())) {
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
                errorLabel.setText("Min level must be smaller than the max.");
                return;
            }

            // Saving new outsourced part to inventory
            errorLabel.setText("");
            System.out.println("Save Button Pushed");
            ((Outsourced) companyName).setCompanyName(machineIdOrCompanyName.getText());
            Inventory.addPart((companyName));
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
    * This method returns to the main screen Inventory Management System without saving any entered data.
    * @param event - This event handler cancels the current changes and returns to the main screen.
    * @throws IOException - This throws IO Exception if an error occurs.
    */
    @FXML void cancelButton (ActionEvent event) throws IOException {
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
            errorLabel.setText("You have returned to the add part screen.");
        }
    }

    /**
     * Method checks to see if price text field value entered is a numeric value only.
     * @param strNum  - This returns a string to verify if price text field is indeed a double
     * @return - This returns either true or false depending on user input for validation purposes.
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
     * This method checks if the integer values for inventory, min, and max text fields are integers and not doubles or floats
     * @param strNum - This string validates that an integer value is not a double or a floating point.
     * @return - This returns true if it is an integer and false if it is a double or floating point for validation purposes.
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