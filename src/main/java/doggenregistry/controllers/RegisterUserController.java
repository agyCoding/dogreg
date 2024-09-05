package doggenregistry.controllers;

import doggenregistry.services.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterUserController {

    UserManager userManager = UserManager.getInstance();

    @FXML
    private TextField regUserName;
    
    @FXML
    private PasswordField regUserPassword;

    @FXML
    private void registerUser() {
        String usernameText = regUserName.getText();
        String passwordText = regUserPassword.getText();

        userManager.registerUser(usernameText, passwordText);
        // Show a success message and switch the screen to login

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Registration Successful");
        alert.setHeaderText("User Registered");
        alert.setContentText("You have successfully registered! Please log in to open the main app");

        alert.showAndWait();

        // Close the registration window
        Stage stage = (Stage) regUserName.getScene().getWindow();
        stage.close();
    }

}
