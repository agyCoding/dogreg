package doggenregistry;

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

public class LoginController {

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private CheckBox rememberMe;

    @FXML
    private void tryToLogin() {
        String testUserName = "test";
        String testPassword = "123";

        // Get info from username and password fields
        String usernameText = userName.getText();
        String passwordText = password.getText();

        if (testUserName.equals(usernameText) && testPassword.equals(passwordText)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
                Parent root = loader.load();

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

        // App.setRoot("secondary");
    }    
}
