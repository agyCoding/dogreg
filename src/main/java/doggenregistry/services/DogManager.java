package doggenregistry.services;

import doggenregistry.models.Dog;

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

    // register a new dog
    public void registerDog(Dog dog) {
        String query = "INSERT INTO dogs (name, breed, birthDate, isFemale, registrationDate, ownerName) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, dog.getName());
            stmt.setString(2, dog.getBreed());
            stmt.setDate(3, java.sql.Date.valueOf(dog.getBirthDate()));
            stmt.setBoolean(4, dog.isFemale());
            stmt.setDate(5, java.sql.Date.valueOf(dog.getRegistrationDate()));
            stmt.setString(6, dog.getOwnerName());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
