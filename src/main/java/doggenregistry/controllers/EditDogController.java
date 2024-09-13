package doggenregistry.controllers;

import doggenregistry.models.Dog;
import doggenregistry.services.DogManager;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

import java.time.LocalDate;

public class EditDogController {
    @FXML
    private TextField dogNameField;

    @FXML
    private DatePicker dogBirthDatePicker;

    @FXML
    private ChoiceBox<String> breedChoiceBox;

    private Dog selectedDog;

    // Method to populate the breed choice box
    public void initialize() {
        DogManager.getInstance().populateBreedBox(breedChoiceBox);
    }

    // Method to set the dog data in the fields for editing
    public void setDogData(Dog dog) {
        this.selectedDog = dog;

        // Fill in the fields with the dog's current data
        dogNameField.setText(dog.getName());
        dogBirthDatePicker.setValue(dog.getBirthDate());

        // Find breed name based on dog's breedID
        String currentBreed = dog.getBreedName();

        // Set the current breed in the ChoiceBox
        breedChoiceBox.getSelectionModel().select(currentBreed);
    }

    // Method to save the edited dog data
    public void saveDogChanges() {
        String updatedName = dogNameField.getText();
        String updatedBreed = breedChoiceBox.getValue();
        LocalDate updatedBirthDate = dogBirthDatePicker.getValue();

        // Update the dog's data
        selectedDog.setName(updatedName);
        selectedDog.setBreedName(updatedBreed);
        // Get the breed ID based on the breed name
        int updatedBreedId = DogManager.getInstance().getBreedIdByName(updatedBreed);
        // Set the updated breed ID
        selectedDog.setBreedID(updatedBreedId);

        selectedDog.setBirthDate(updatedBirthDate);

        // Passing the updated data to DogManager to update the database and getting a boolean back to see if it updated successfuly or not
        boolean dogUpdateStatus = DogManager.getInstance().updateDog(selectedDog);
        if (dogUpdateStatus) {
            // Close the window
            Stage stage = (Stage) dogNameField.getScene().getWindow();
            stage.close();
        }
    }
}
