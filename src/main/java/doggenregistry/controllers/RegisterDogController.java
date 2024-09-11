package doggenregistry.controllers;

import doggenregistry.services.DogManager;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class RegisterDogController {
    DogManager dogManager = DogManager.getInstance();

    private String userName;

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

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
