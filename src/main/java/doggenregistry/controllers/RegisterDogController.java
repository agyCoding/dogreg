package doggenregistry.controllers;

import doggenregistry.models.User;
import doggenregistry.models.Dog;
import doggenregistry.services.DogManager;
import doggenregistry.services.UserManager;
import doggenregistry.utils.GeneralUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class RegisterDogController {
    DogManager dogManager = DogManager.getInstance();

    private String userName;

    private int userID;

    @FXML
    private TextField nameTextField;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private ChoiceBox<String> breedBox;

    @FXML
    private ChoiceBox<String> isFemaleBox;

    @FXML
    public void initialize() {
        dogManager.populateBreedBox(breedBox);
        dogManager.populateIsFemaleBox(isFemaleBox);
    }

    // Save userName passed from previous scene, so it can be used to retrieve userID
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Assign userID based on the userName passed from previous scene, for further use in dog registration
    public void setUserID(String userName) {
        this.userID = UserManager.getInstance().fetchUserIDFromDatabase(userName);
    }

    // Register a new dog
    public void registerDog() {
        // Here I will create a new dog object based on the information chosen by the user
        // I will also put in some exception handling f.ex. if the breed is not chosen or sex is not chosen
        // The actual registration of the dog (in the database) will be done in the DogManager class
        String dogsName = nameTextField.getText();
        String dogsBreed = breedBox.getValue();
        String dogsSex = isFemaleBox.getValue();
        // In the database the information about the dog's sex is stored as a boolean, so we need to convert it here
        boolean isFemale;
        if (dogsSex.equals("FEMALE")) {
            isFemale = true;
        } else {
            isFemale = false;
        }
        LocalDate dogsBirthDate = birthDatePicker.getValue();
        // FOR DEBUGGING
        System.out.println(dogsBirthDate);

        setUserID(userName);

        if(dogsName.isEmpty() || dogsBreed == null || dogsSex == null || dogsBirthDate == null) {
            GeneralUtils.showAlert(AlertType.ERROR, "Information missing", "Information is missing","Please fill in all the fields");
            return;
        } else {
            // If everything is ok, create a new dog object and register it
            Dog newDog = new Dog(dogsName, dogsBreed, dogsBirthDate, isFemale, userID);

            try {
                dogManager.registerDog(newDog);
            } catch (Exception e) {
                GeneralUtils.showAlert(AlertType.ERROR, "Error", "Error registering dog", "An error occurred while registering the dog");
            }
        }
    }
}
