package doggenregistry.controllers;

import doggenregistry.models.User;
import doggenregistry.services.DogManager;
import doggenregistry.services.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
}
