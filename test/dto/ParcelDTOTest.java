package dto;

import org.junit.Test;
import static org.junit.Assert.*;

public class ParcelDTOTest {
    
    public ParcelDTOTest() {
    }
    
    @Test
    public void testGetValues() {
        System.out.println("___ ParcelDTO: Get values");
        
        UserDTO seller = new UserDTO(3, "Anders", "Learmonth", "seller", "123", "1900-01-01", "1900-01-01", "a", "a", "a", "a", "a@b.com", "", true, "Recipient");
                
        ParcelDTO instance = new ParcelDTO(1, "ParcelName", "ParcelType", 90, seller, "1900-01-01", "1900-01-02", 2);
        
        boolean passed = true;
        
        if (
            instance.getId() != 1 ||
            !instance.getName().equalsIgnoreCase("ParcelName") ||
            !instance.getType().equalsIgnoreCase("ParcelType") ||
            instance.getWeightGrams() != 90 ||
            !instance.getSeller().getUsername().equalsIgnoreCase("seller") ||
            !instance.getDateAdded().equalsIgnoreCase("1900-01-01") ||
            !instance.getDateModified().equalsIgnoreCase("1900-01-02") ||
            instance.getQuantityInOrder() != 2
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }    
}
