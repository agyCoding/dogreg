package doggenregistry.controllers;

import doggenregistry.services.UserManager;
import doggenregistry.models.User;
import doggenregistry.utils.PasswordUtils;
import doggenregistry.utils.GeneralUtils;

import javafx.fxml.FXML;
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
    private PasswordField regUserPasswordRepeat;

    @FXML
    private void registerUser() {
        String usernameText = regUserName.getText();
        String passwordText = regUserPassword.getText();
        String repeatPasswordText = regUserPasswordRepeat.getText();

        if (passwordText.isEmpty() || usernameText.isEmpty()) {
            GeneralUtils.showAlert(AlertType.ERROR, "Registration Failed", "Username or Password is empty", "Please fill in both the username and password fields");
            return;
        } else if (passwordText.equals(repeatPasswordText) == false) {
            GeneralUtils.showAlert(AlertType.ERROR, "Registration Failed", "Passwords do not match", "Please make sure the passwords match");
            return;
        } else {
            // Check if the username is already taken
            if (userManager.isUsernameTaken(usernameText)) {
                GeneralUtils.showAlert(AlertType.ERROR, "Registration Failed", "Username already taken", "Please choose a different username");
                return;
            }
            // If everything's ok, create new user object and register it
            User newUser = new User(usernameText, PasswordUtils.hashPassword(passwordText));
            userManager.registerUser(newUser);
            
            // Show a success message and switch the screen to login
    
            GeneralUtils.showAlert(AlertType.INFORMATION, "Registration Successful", "User Registered", "You have successfully registered! Please log in to open the main app");

            // Close the registration window
            Stage stage = (Stage) regUserName.getScene().getWindow();
            stage.close();
        }

    }

}
