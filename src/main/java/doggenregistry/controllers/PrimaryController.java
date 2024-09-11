package doggenregistry.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private Label userLabel;

    private String userName;

    public void updateUserLabel(String username) {
        this.userName = username;
        userLabel.setText("Logged in as: " + username);
    }

    public void switchToDogRegistration() {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/doggenregistry/registerdog.fxml"));
                Parent root = loader.load();

                // Get the controller of the next scene
                RegisterDogController registerDogController = loader.getController();

                registerDogController.setUserName(userName);

                 // Get the current stage (window) and set the new scene
                 Stage stage = (Stage) userLabel.getScene().getWindow();
                 stage.setScene(new Scene(root));
                 stage.show();              
            } catch (IOException e) {
                e.printStackTrace();

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Unable to load the dog registration screen");
                alert.setContentText("En error occured while trying to load the dog registration screen");
                alert.showAndWait();
            }
    }


}
