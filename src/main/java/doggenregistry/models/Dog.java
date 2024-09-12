package doggenregistry.models;
import java.time.LocalDate;

public class Dog {
    private String name;
    private String breed;
    private LocalDate birthDate;
    // is the dog female?
    private boolean isFemale;
    private LocalDate registrationDate;
    private int ownerID;
    // does the dog have Degen Myelopathy?
    private boolean hasDM;

    public Dog(String name, String breed, LocalDate birthDate, boolean isFemale, int ownerID) {
        this.name = name;
        this.breed = breed;
        this.birthDate = birthDate;
        this.isFemale = isFemale;
        this.registrationDate = LocalDate.now();
        this.ownerID = ownerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
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
