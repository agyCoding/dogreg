package doggenregistry.controllers;

import doggenregistry.services.UserManager;
import doggenregistry.services.DatabaseManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterUserController {

    UserManager userManager = UserManager.getInstance();

    @FXML
    private TextField regUserName;
    
    @FXML
    private PasswordField regUserPassword;

    @FXML
    private PasswordField regUserPasswordRepeat;

    @FXML
    private void registerUser() {
        String usernameText = regUserName.getText();
        String passwordText = regUserPassword.getText();
        String repeatPasswordText = regUserPasswordRepeat.getText();

        if (passwordText.isEmpty() || usernameText.isEmpty()) {
            showAlert(AlertType.ERROR, "Registration Failed", "Username or Password is empty", "Please fill in both the username and password fields");
            return;
        } else if (passwordText.equals(repeatPasswordText) == false) {
            showAlert(AlertType.ERROR, "Registration Failed", "Passwords do not match", "Please make sure the passwords match");
            return;
        } else {
            // Check if the username is already taken
            if (userManager.isUsernameTaken(usernameText)) {
                showAlert(AlertType.ERROR, "Registration Failed", "Username already taken", "Please choose a different username");
                return;
            }
            userManager.registerUser(usernameText, passwordText);
            // Show a success message and switch the screen to login
    
            showAlert(AlertType.INFORMATION, "Registration Successful", "User Registered", "You have successfully registered! Please log in to open the main app");

            // Close the registration window
            Stage stage = (Stage) regUserName.getScene().getWindow();
            stage.close();
        }

    }


    private void showAlert(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
