package doggenregistry.models;

public class User {
    private String userName;
    private String passwordHash;
    private int userID;

    public User(String userName, String passwordHash) {
        this.userName = userName;
        this.passwordHash = passwordHash;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserID() {
        return userID;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
