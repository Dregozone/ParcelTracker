package dto;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserDTOTest {
    
    public UserDTOTest() {
    }

    @Test
    public void testGetValues() {
        System.out.println("___ TransactionDTO: Get values");
        
        UserDTO instance = new UserDTO(3, "Anders", "Learmonth", "seller", "123", "1900-01-01", "1900-01-01", "a", "a", "a", "a", "a@b.com", "a", true, "Seller");
        
        boolean passed = true;
        
        if (
            instance.getId() != 3 ||
            !instance.getFirstName().equalsIgnoreCase("Anders") ||
            !instance.getLastName().equalsIgnoreCase("Learmonth") ||
            !instance.getUsername().equalsIgnoreCase("seller") ||
            !instance.getHashedPassword().equalsIgnoreCase("123") ||
            !instance.getDateAdded().equalsIgnoreCase("1900-01-01") ||
            !instance.getDateModified().equalsIgnoreCase("1900-01-01") ||
            !instance.getAddressLineOne().equalsIgnoreCase("a") ||
            !instance.getTown().equalsIgnoreCase("a") ||
            !instance.getCounty().equalsIgnoreCase("a") ||
            !instance.getPostcode().equalsIgnoreCase("a") ||
            !instance.getEmail().equalsIgnoreCase("a@b.com") ||
            !instance.getPhone().equalsIgnoreCase("a") ||
            !instance.isIsActive() ||
            !instance.getRole().equalsIgnoreCase("Seller")
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }
}
