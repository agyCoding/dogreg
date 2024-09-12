package doggenregistry.controllers;

import doggenregistry.models.Dog;
import doggenregistry.services.DogManager;
import doggenregistry.services.UserManager;
import doggenregistry.utils.GeneralUtils;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.ArrayList;


public class PrimaryController {

    @FXML
    private Label userLabel;

    @FXML
    private ListView<String> yourDogsLV;

    private String userName;

    public void updateUserLabel(String username) {
        this.userName = username;
        userLabel.setText("Logged in as: " + username);
        loadUserDogs(); // Call the method to load the dogs
    }

    public void loadUserDogs() {
        // Here I load the dogs from the database and display them in the ListView
        // I use the DogManager class to fetch the dogs from the database
        // I also add a listener to the ListView to display the details of the selected dog
        int userID = UserManager.getInstance().fetchUserIDFromDatabase(userName);

        // Fetch dog list for current user
        List<Dog> dogs = DogManager.getInstance().fetchDogsForUser(userID);

        // Create a list of dog names
        ObservableList<String> dogNames = FXCollections.observableArrayList();

        // Add each dog's name to the observable list
        for (Dog dog : dogs) {
            dogNames.add(dog.getName());
        }

        // Set the list of dog names to the ListView
        yourDogsLV.setItems(dogNames);

        // Add event listener for list view item selection
        yourDogsLV.setOnMouseClicked(event -> {
            String selectedDogName = yourDogsLV.getSelectionModel().getSelectedItem();
            if (selectedDogName != null) {
                Dog selectedDog = getSelectedDog(selectedDogName);
                openEditDogWindow(selectedDog);
            }
        });
    }

    // Methos to get the selected dog (object) from the list view
    private Dog getSelectedDog(String dogName) {
        for (Dog dog : dogs) {
            if (dog.getName().equals(dogName)) {
                return dog;
            }
        }
        return null;
    }

    public void switchToDogRegistration() {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/doggenregistry/registerdog.fxml"));
                Parent root = loader.load();

                // Create a new stage for the registration window
                Stage registrationStage = new Stage();
                registrationStage.setScene(new Scene(root));
                registrationStage.setTitle("Register New Dog");

                // Store a reference to the stage
                registrationStage.initOwner(((Stage) userLabel.getScene().getWindow())); // Set the owner of the new stage
                registrationStage.setOnHidden(e -> registrationStage.close()); // Close the stage when it's hidden

                registrationStage.show();   

              
            } catch (IOException e) {
                e.printStackTrace();

                GeneralUtils.showAlert(AlertType.ERROR, "Error", "Unable to load the dog registration screen", "En error occured while trying to load the dog registration screen");

            }
    }

    // Method to open the edit dog window
    private void openEditDogWindow(Dog selectedDog) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/doggenregistry/editdog.fxml"));
            Parent root = loader.load();

            // Get the controller of the next scene
            EditDogController editDogController = loader.getController();

            // Pass the selected dog to the next scene's controller
            editDogController.setSelectedDog(selectedDog);

            // Create a new stage (window)
            Stage editStage = new Stage();
            editStage.setScene(new Scene(root));
            editStage.setTitle("Edit Dog");

            // Make the window modal (blocks the parent window until closed)
            editStage.initModality(Modality.WINDOW_MODAL);
            editStage.initOwner(yourDogsLV.getScene().getWindow());

            editStage.showAndWait();  // Show and wait until the window is closed       
        } catch (IOException e) {
            e.printStackTrace();

            GeneralUtils.showAlert(AlertType.ERROR, "Error", "Unable to load the edit dog screen", "En error occured while trying to load the edit dog screen");
        }

    }


}
