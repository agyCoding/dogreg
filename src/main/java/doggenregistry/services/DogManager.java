package doggenregistry.services;

import doggenregistry.models.Dog;
import doggenregistry.utils.GeneralUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.time.LocalDate;

public class DogManager {
    private static DogManager instance;

    private Map<String, Integer> breedNameToIdMap = new HashMap<>();

    private DogManager() {   }

    public static synchronized DogManager getInstance() {
        if (instance == null) {
            instance = new DogManager();
        }
        return instance;
    }

    // Method to fetch dogs for a specific user
    public List<Dog> fetchDogsForUser(int userID) {
        List<Dog> dogs = new ArrayList<>();
        String query = "SELECT * FROM dogs WHERE owner_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int dogsId = rs.getInt("id");
                String dogsName = rs.getString("name");
                int breedID = rs.getInt("breed_id");
                LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
                boolean isFemale = rs.getBoolean("is_female");
                LocalDate registrationDate = rs.getDate("registration_date").toLocalDate();
                boolean hasDM = rs.getBoolean("has_dm");

                // Create a new Dog object
                Dog dog = new Dog(dogsId, dogsName, breedID, birthDate, isFemale, registrationDate, userID, hasDM);
                dogs.add(dog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dogs;
    }

    // Populating the sex choice box with hardcoded values
    public void populateIsFemaleBox(ChoiceBox<String> isFemaleBox) {
        ObservableList<String> sexes = FXCollections.observableArrayList("FEMALE", "MALE");
        isFemaleBox.setItems(sexes);
    }

    // Populating the breed choice box from the database
    public void populateBreedBox(ChoiceBox<String> breedChoiceBox) {
        ObservableList<String> breeds = FXCollections.observableArrayList();
        String query = "SELECT breed_name, id FROM breeds"; // Update with your actual breed table

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String breed = resultSet.getString("breed_name");
                int breedID = resultSet.getInt("id");
                breeds.add(breed);
                breedNameToIdMap.put(breed, breedID); // populate the map
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        breedChoiceBox.setItems(breeds);
    }

    // Method to get breed ID from breed name
    public Integer getBreedIdByName(String breedName) {
        return breedNameToIdMap.get(breedName);
    }

    // Method to get breed name from breed ID
    public String getBreedNameById(int breedId) {
    for (Map.Entry<String, Integer> entry : breedNameToIdMap.entrySet()) {
        if (entry.getValue() == breedId) {
            return entry.getKey(); // Return the breed name
        }
    }
    return null; // If not found, return null or handle it accordingly
    }

    // Register a new dog in the database
    public void registerDog(Dog dog) {
        String query = "INSERT INTO dogs (name, breed_id, birth_date, is_female, registration_date, owner_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, dog.getName());

            // Get breed ID from breed name
            Integer breedID = getBreedIdByName(dog.getBreedName());
            if (breedID == null) {
                throw new SQLException("Breed ID not found for breed: " + dog.getBreedName());
            }
            stmt.setInt(2, breedID);  // use breed ID;

            stmt.setDate(3, java.sql.Date.valueOf(dog.getBirthDate()));
            stmt.setBoolean(4, dog.isFemale());
            stmt.setDate(5, java.sql.Date.valueOf(dog.getRegistrationDate()));
            stmt.setInt(6, dog.getOwnerID());

            stmt.executeUpdate();

            GeneralUtils.showAlert(AlertType.INFORMATION, "Dog registered", "Dog registered successfully", "The dog has been registered successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing dog in the database
    public boolean updateDog(Dog dog) {
        String query = "UPDATE dogs SET name = ?, breed_id = ?, birth_date = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, dog.getName());
            stmt.setInt(2,dog.getBreedID());
            stmt.setDate(3, java.sql.Date.valueOf(dog.getBirthDate()));
            stmt.setInt(4, dog.getId());
            // Just for debugging
            System.out.println("Dog's id is: " + dog.getId());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                GeneralUtils.showAlert(AlertType.INFORMATION, "Success", "Dog updated", "The dog has been updated successfully.");
                return true;
            } else {
                GeneralUtils.showAlert(AlertType.ERROR, "Error", "Update Failed", "No rows were updated.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
