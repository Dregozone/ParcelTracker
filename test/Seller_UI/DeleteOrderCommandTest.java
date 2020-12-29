package Seller_UI;

import Driver_UI.ViewAllOrdersCommand;
import dto.OrderDTO;
import dto.UserDTO;
import java.util.ArrayList;
import managedbean.SellerBean;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeleteOrderCommandTest {
    
    public DeleteOrderCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("___ SellerDeleteOrderCommand execute");
        
        // Prep create order details
        SellerBean sellerInstance = new SellerBean();
        int orderId = sellerInstance.getNextOrderId();
        UserDTO seller = new UserDTO(3, "a", "a", "seller", "abc", "1900-01-01", "1900-01-01", "a", "a", "a", "a", "a", "a", true, "Seller");
        UserDTO recipient = seller;
        UserDTO driver = seller;
        OrderDTO order = new OrderDTO(orderId, recipient, driver, seller, "1900-01-01", true, "1900-01-01");
        
        // Count current orders
        ViewAllOrdersCommand viewOrdersInstance = new ViewAllOrdersCommand();
        int initialOrderCount = ((ArrayList<OrderDTO>)viewOrdersInstance.execute()).size();
        
        // Create order
        CreateOrderCommand createInstance = new CreateOrderCommand(order);
        Object result = createInstance.execute();
        
        // Delete order
        DeleteOrderCommand deleteInstance = new DeleteOrderCommand(orderId);
        deleteInstance.execute();
        
        // Check values
        int laterOrderCount = ((ArrayList<OrderDTO>)viewOrdersInstance.execute()).size();
        
        assertTrue(initialOrderCount == laterOrderCount);
    }
}
