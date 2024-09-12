package doggenregistry.controllers;

import doggenregistry.models.Dog;
import doggenregistry.services.DogManager;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

    // Method to set the dog data in the fields for editing
    public void setDogData(Dog dog) {
        this.selectedDog = dog;

        // Fill in the fields with the dog's current data
        dogNameField.setText(dog.getName());
        dogBirthDatePicker.setValue(dog.getBirthDate());
        breedChoiceBox.setValue(dog.getBreed());
    }

    // Method to save the edited dog data
    public void saveDogChanges() {
        String updatedName = dogNameField.getText();
        String updatedBreed = breedChoiceBox.getValue();
        LocalDate updatedBirthDate = dogBirthDatePicker.getValue();

        // Update the dog's data
        selectedDog.setName(updatedName);
        selectedDog.setBreed(updatedBreed);
        selectedDog.setBirthDate(updatedBirthDate);

        // Passing the updated data to DogManager to update the database
        DogManager.getInstance().updateDog(selectedDog);
    }
}
