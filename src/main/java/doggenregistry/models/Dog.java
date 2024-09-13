package doggenregistry.models;
import java.time.LocalDate;

import doggenregistry.services.DogManager;

public class Dog {
    private int id;
    private String name;
    private int breedID;
    private String breedName;
    private LocalDate birthDate;
    // is the dog female?
    private boolean isFemale;
    private LocalDate registrationDate;
    private int ownerID;
    // does the dog have Degen Myelopathy?
    private boolean hasDM;

    // Constructor for creating a dog from the database (with ID)
    public Dog(int id, String name, int breedID, LocalDate birthDate, boolean isFemale, LocalDate registrationDate, int ownerID, boolean hasDM) {
        this.id = id;
        this.name = name;
        this.breedID = breedID;
        // "Translate" the breed ID to a breed name
        this.breedName = DogManager.getInstance().getBreedNameById(breedID);

        this.birthDate = birthDate;
        this.isFemale = isFemale;
        this.registrationDate = registrationDate;
        this.ownerID = ownerID;
        this.hasDM = hasDM;
    }

    // Constructor for creating a new dog from within the application
    public Dog(String name, String breedName, LocalDate birthDate, boolean isFemale, int ownerID) {
        this.name = name;
        this.breedName = breedName;
        this.birthDate = birthDate;
        this.isFemale = isFemale;
        this.registrationDate = LocalDate.now();
        this.ownerID = ownerID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBreedID() {
        return breedID;
    }

    public void setBreedID(int breedID) {
        this.breedID = breedID;
    }

    public String getBreedName() {
        // If the dog object is created from the table dogs in the database, it will only have breedId. To populate breedName, we need to fetch it from the hashmap
        if (breedName == null || breedName.isEmpty()) {
            // If breedName is not set, fetch it using breedID
            return DogManager.getInstance().getBreedNameById(breedID);
        }
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public void setFemale (boolean isFemale) {
        this.isFemale = isFemale;
    }   

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public boolean hasDM() {
        return hasDM;
    }

    public void setHasDM(boolean hasDM) {
        this.hasDM = hasDM;
    }
}
