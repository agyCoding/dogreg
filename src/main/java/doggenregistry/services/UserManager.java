package doggenregistry.services;

import doggenregistry.models.User;
import doggenregistry.utils.PasswordUtils;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static UserManager instance;
    private List<User> users;
    
    private UserManager() {
        users = new ArrayList<>();
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    // Register a new user
    public void registerUser(String userName, String password) {
        String hashedPassword = PasswordUtils.hashPassword(password);
        User newUser = new User(userName, hashedPassword);
        users.add(newUser);
    }

    // Check if the user can login
    public boolean canLogin(String userName, String password) {
        String hashedPassword = PasswordUtils.hashPassword(password);

        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPasswordHash().equals(hashedPassword)) {
                return true;
            }
        }
        return false;
    }
}
