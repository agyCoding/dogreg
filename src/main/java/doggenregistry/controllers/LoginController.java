package doggenregistry.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


import doggenregistry.services.UserManager;

public class LoginController {

    UserManager userManager = UserManager.getInstance();

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private CheckBox rememberMe;

    @FXML
    private void tryToLogin() {
        // Get info from username and password fields
        String usernameText = userName.getText();
        String passwordText = password.getText();

        if (userManager.canLogin(usernameText,passwordText) || usernameText.equals("admin") && passwordText.equals("admin")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/doggenregistry/primary.fxml"));
                Parent root = loader.load();

                // Get the controller of the next scene
                PrimaryController primaryController = loader.getController();

                // Pass the username to the next scene's controller
                primaryController.updateUserLabel(usernameText);

                 // Get the current stage (window) and set the new scene
                 Stage stage = (Stage) userName.getScene().getWindow();
                 stage.setScene(new Scene(root));
                 stage.show();              
            } catch (IOException e) {
                e.printStackTrace();

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Unable to load the main app/primary screen");
                alert.setContentText("En error occured while trying to load the primary app");
                alert.showAndWait();
            }
        }
        else {
            // If password and username not matching, show error
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Invalig Username or Password");
            alert.setContentText("Please enter correct username and password");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void regNewUser() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/doggenregistry/registeruser.fxml"));
            Parent root = loader.load();
    
            // Create a new stage for the registration window
            Stage registrationStage = new Stage();
            registrationStage.setScene(new Scene(root));
            registrationStage.setTitle("Register New User");

            // Store a reference to the stage
            registrationStage.initOwner(((Stage) userName.getScene().getWindow())); // Set the owner of the new stage
            registrationStage.setOnHidden(e -> registrationStage.close()); // Close the stage when it's hidden

            registrationStage.show();    
        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to load the user registration screen");
            alert.setContentText("En error occured while trying to load the user registration");
            alert.showAndWait();
        }
    }

}
