package dto;

import java.io.Serializable;

public class UserDTO implements Serializable
{

    private final int id;
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String hashedPassword;
    private final String dateAdded;
    private final String dateModified;
    private final String addressLineOne;
    private final String town;
    private final String county;
    private final String postcode;
    private final String email;
    private final String phone;
    private final boolean isActive;
    private final String role;

    public UserDTO(int id, String firstName, String lastName, String username, String hashedPassword, String dateAdded, String dateModified, String addressLineOne, String town, String county, String postcode, String email, String phone, boolean isActive, String role)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
        this.addressLineOne = addressLineOne;
        this.town = town;
        this.county = county;
        this.postcode = postcode;
        this.email = email;
        this.phone = phone;
        this.isActive = isActive;
        this.role = role;
    }

    public int getId()
    {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getDateModified() {
        return dateModified;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public String getTown() {
        return town;
    }

    public String getCounty() {
        return county;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public String getRole() {
        return role;
    }
}
