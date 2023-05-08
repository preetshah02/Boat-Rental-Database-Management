public class User {
    private String firstName;
    private String lastName;
    private String driversLicense;
    private String username;
    private boolean admin;

    public User(String firstName, String lastName, String driversLicense, String username, boolean admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.driversLicense = driversLicense;
        this.username = username;
        this.admin = admin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDriversLicense() {
        return driversLicense;
    }

    public String getUsername() {
        return username;
    }

    public boolean getAdmin() {
        return admin;
    }
}
