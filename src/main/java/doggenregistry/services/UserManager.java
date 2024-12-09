package doggenregistry.services;

import doggenregistry.models.User;
import doggenregistry.utils.PasswordUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {

    private static UserManager instance;

    private UserManager() {   }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    // Method to check if username is taken
    public boolean isUsernameTaken(String userName) {
        String query = "SELECT COUNT(*) FROM users WHERE userName = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;  // If count > 0, username exists
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // Return false if there's any error or no username found
    }

    // New method to register a user (object based)
    public void registerUser(User user) {
        // Ensure user object is not null
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        String query = "INSERT INTO users (userName, email, hashedPassword) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check if the user can login
    public boolean canLogin(String userName, String password) {
        String hashedPassword = PasswordUtils.hashPassword(password);

        String query = "SELECT * FROM users WHERE userName = ? AND hashedPassword = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, userName);
            stmt.setString(2, hashedPassword);

            ResultSet rs = stmt.executeQuery();

            // If there's a match, return true
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    // Fetch userID based on username
    public int fetchUserIDFromDatabase(String userName) {
        String query = "SELECT id FROM users WHERE userName = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userID = rs.getInt("id");
                return userID;
            } else {
                return -1; // if no user is found, return -1
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // if there's an error, return -1
        }
    }

}
