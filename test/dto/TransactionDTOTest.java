package dto;

import org.junit.Test;
import static org.junit.Assert.*;

public class TransactionDTOTest {
    
    public TransactionDTOTest() {
    }

    @Test
    public void testGetValues() {
        System.out.println("___ TransactionDTO: Get values");
        
        UserDTO seller = new UserDTO(3, "Anders", "Learmonth", "seller", "123", "1900-01-01", "1900-01-01", "a", "a", "a", "a", "a@b.com", "", true, "Recipient");
                
        TransactionDTO instance = new TransactionDTO(1, 2, "Picked up", seller, "1900-01-01");
        
        boolean passed = true;
        
        if (
            instance.getId() != 1 ||
            instance.getOrderId() != 2 ||
            !instance.getName().equalsIgnoreCase("Picked up") ||
            !instance.getAddedBy().getUsername().equalsIgnoreCase("seller") ||
            !instance.getDateAdded().equalsIgnoreCase("1900-01-01")
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }
}
