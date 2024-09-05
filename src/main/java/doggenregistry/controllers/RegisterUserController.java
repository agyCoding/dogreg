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
    private PasswordField regUserPasswordRepeat;

    @FXML
    private void registerUser() {
        String usernameText = regUserName.getText();
        String passwordText = regUserPassword.getText();
        String repeatPasswordText = regUserPasswordRepeat.getText();

        if (passwordText.isEmpty() || usernameText.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Registration Failed");
            alert.setHeaderText("Username or Password is empty");
            alert.setContentText("Please fill in both the username and password fields");
            alert.showAndWait();
            return;
        } else if (passwordText.equals(repeatPasswordText) == false) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Registration Failed");
            alert.setHeaderText("Passwords do not match");
            alert.setContentText("Please make sure the passwords match");
            alert.showAndWait();
            return;
        } else {
            // Check if the username is already taken
            for (int i = 0; i < userManager.getUsers().size(); i++) {
                if (userManager.getUsers().get(i).getUserName().equals(usernameText)) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Registration Failed");
                    alert.setHeaderText("Username already taken");
                    alert.setContentText("Please choose a different username");
                    alert.showAndWait();
                    return;
                }
            }
            userManager.registerUser(usernameText, passwordText);
            // Show a success message and switch the screen to login
    
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText("User Registered");
            alert.setContentText("You have successfully registered! Please log in to open the main app");
    
            alert.showAndWait();
        }
        // Close the registration window
        Stage stage = (Stage) regUserName.getScene().getWindow();
        stage.close();
    }

}
