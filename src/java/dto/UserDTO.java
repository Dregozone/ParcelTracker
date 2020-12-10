package dto;

import java.io.Serializable;
import java.util.Objects;

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

    /*
    @Override
    public boolean equals(Object obj) {
        
        if ( obj == null ) {
            
            return false;
        }
        
        if ( !(obj instanceof UserDTO) ) {
            
            return false;
        }
        
        if ( obj == this ) {
            
            return true;
        }
        
        UserDTO obj1 = (UserDTO)obj;
        
        return (int)obj1.id == (int)id &&
                obj1.firstName.equalsIgnoreCase(firstName) &&
                obj1.lastName.equalsIgnoreCase(lastName) &&
                obj1.username.equalsIgnoreCase(username) &&
                obj1.hashedPassword.equalsIgnoreCase(hashedPassword) &&
                obj1.dateAdded.equalsIgnoreCase(dateAdded) &&
                obj1.dateModified.equalsIgnoreCase(dateModified) &&
                obj1.addressLineOne.equalsIgnoreCase(addressLineOne) &&
                obj1.town.equalsIgnoreCase(town) &&
                obj1.county.equalsIgnoreCase(county) &&
                obj1.postcode.equalsIgnoreCase(postcode) &&
                obj1.email.equalsIgnoreCase(email) &&
                obj1.phone.equalsIgnoreCase(phone) &&
                obj1.isActive == isActive &&
                obj1.role.equalsIgnoreCase(role);
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + this.code.hashCode();
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.rate) ^ (Double.doubleToLongBits(this.rate) >>> 32));
        return hash;
    }
    */

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.firstName);
        hash = 23 * hash + Objects.hashCode(this.lastName);
        hash = 23 * hash + Objects.hashCode(this.username);
        hash = 23 * hash + Objects.hashCode(this.hashedPassword);
        hash = 23 * hash + Objects.hashCode(this.dateAdded);
        hash = 23 * hash + Objects.hashCode(this.dateModified);
        hash = 23 * hash + Objects.hashCode(this.addressLineOne);
        hash = 23 * hash + Objects.hashCode(this.town);
        hash = 23 * hash + Objects.hashCode(this.county);
        hash = 23 * hash + Objects.hashCode(this.postcode);
        hash = 23 * hash + Objects.hashCode(this.email);
        hash = 23 * hash + Objects.hashCode(this.phone);
        hash = 23 * hash + (this.isActive ? 1 : 0);
        hash = 23 * hash + Objects.hashCode(this.role);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.isActive != other.isActive) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.hashedPassword, other.hashedPassword)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        if (!Objects.equals(this.dateModified, other.dateModified)) {
            return false;
        }
        if (!Objects.equals(this.addressLineOne, other.addressLineOne)) {
            return false;
        }
        if (!Objects.equals(this.town, other.town)) {
            return false;
        }
        if (!Objects.equals(this.county, other.county)) {
            return false;
        }
        if (!Objects.equals(this.postcode, other.postcode)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return true;
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
