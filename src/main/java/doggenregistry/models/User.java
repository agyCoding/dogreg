package doggenregistry.models;

public class User {
    // This class is a model class that represents a User object
    // id is not included in the constructor because it is auto-generated in the database
    private int id;
    private String userName;
    private String email;
    private String passwordHash;

    public User(String userName, String email, String passwordHash) {
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public User(String userName, String passwordHash) {
        this.userName = userName;
        //generate fake email from username
        this.email = userName + "@example.com";
        this.passwordHash = passwordHash;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
