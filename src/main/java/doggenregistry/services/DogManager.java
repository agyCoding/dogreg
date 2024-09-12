package doggenregistry.services;

import doggenregistry.models.Dog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DogManager {
    private static DogManager instance;

    private DogManager() {   }

    public static synchronized DogManager getInstance() {
        if (instance == null) {
            instance = new DogManager();
        }
        return instance;
    }

    // Populating the sex choice box with hardcoded values
    public void populateIsFemaleBox(ChoiceBox<String> isFemaleBox) {
        ObservableList<String> sexes = FXCollections.observableArrayList("FEMALE", "MALE");
        isFemaleBox.setItems(sexes);
    }

    // Populating the breed choice box from the database
    public void populateBreedBox(ChoiceBox<String> breedChoiceBox) {
        ObservableList<String> breeds = FXCollections.observableArrayList();
        String query = "SELECT breed_name FROM breeds"; // Update with your actual breed table

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String breed = resultSet.getString("breed_name");
                breeds.add(breed);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        breedChoiceBox.setItems(breeds);
    }
/*
    // register a new dog
    public void registerDog(Dog dog) {
        String query = "INSERT INTO dogs (name, breed, birthDate, isFemale, registrationDate, ownerID) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, dog.getName());
            stmt.setString(2, dog.getBreed());
            stmt.setDate(3, java.sql.Date.valueOf(dog.getBirthDate()));
            stmt.setBoolean(4, dog.isFemale());
            stmt.setDate(5, java.sql.Date.valueOf(dog.getRegistrationDate()));
            stmt.setString(6, dog.getOwnerID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 * 
 */

}
