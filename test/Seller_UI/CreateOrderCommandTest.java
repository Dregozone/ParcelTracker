package Seller_UI;

import dto.OrderDTO;
import dto.UserDTO;
import managedbean.SellerBean;
import org.junit.Test;
import static org.junit.Assert.*;

public class CreateOrderCommandTest {
    
    public CreateOrderCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("__ SellerCreateOrderCommand execute");

        SellerBean sellerInstance = new SellerBean();
        UserDTO seller = new UserDTO(3, "a", "a", "seller", "abc", "1900-01-01", "1900-01-01", "a", "a", "a", "a", "a", "a", true, "Seller");
        UserDTO recipient = seller;
        UserDTO driver = seller;
        OrderDTO order = new OrderDTO(sellerInstance.getNextOrderId(), recipient, driver, seller, "1900-01-01", true, "1900-01-01");
        
        CreateOrderCommand instance = new CreateOrderCommand(order);

        String expResult = "None";
        Object result = instance.execute();

        OrderDTO orderDetails = (OrderDTO)result;
        
        System.out.println( orderDetails.getDriver().getId() );
        
        assertEquals(expResult, result);
    }    
}
