package Seller_UI;

import dto.OrderDTO;
import dto.UserDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class EditOrderCommandTest {
    
    public EditOrderCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("___ SellerEditOrderCommand execute");

        UserDTO seller = new UserDTO(4, "a", "a", "user", "a", "2020-01-03", "1900-01-01", "a", "a", "a", "a", "a", "a", true, "Seller");
        UserDTO recipient = seller;
        UserDTO driver = seller;
        OrderDTO order = new OrderDTO(1, recipient, driver, seller, "1900-01-01", true, "1900-01-01");
        
        EditOrderCommand instance = new EditOrderCommand(order);

        String expResult = "2020-01-03";
        Object result = instance.execute();

        OrderDTO orderDetails = (OrderDTO)result;
        
        assertEquals( expResult, orderDetails.getDateAdded());
    }   
}
