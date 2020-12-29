package dto;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrderDTOTest {
    
    public OrderDTOTest() {
    }

    @Test
    public void testGetValues() {
        System.out.println("___ OrderDTO: Get values");
        
        UserDTO recipient = new UserDTO(1, "Anders", "Learmonth", "NewUser", "123", "1900-01-01", "1900-01-01", "a", "a", "a", "a", "a@b.com", "", true, "Recipient");
        UserDTO driver = recipient;
        UserDTO seller = recipient;
                
        OrderDTO instance = new OrderDTO(1, recipient, driver, seller, "1900-01-01", true, "2010-01-01");
        
        boolean passed = true;
        
        if (
            instance.getId() != 1 || 
            !instance.getRecipient().getUsername().equalsIgnoreCase("NewUser") ||
            !instance.getDriver().getUsername().equalsIgnoreCase("NewUser") ||
            !instance.getSeller().getUsername().equalsIgnoreCase("NewUser") ||
            !instance.getDateAdded().equalsIgnoreCase("1900-01-01") ||
            !instance.isIsComplete() ||
            !instance.getDateCompleted().equals("2010-01-01")
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }
}
