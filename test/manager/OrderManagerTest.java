package manager;

import dto.OrderDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderManagerTest {
    
    public OrderManagerTest() {
    }

    @Test
    public void testFindOrder() {
        System.out.println("__ findOrder");
        
        int OrderID = 0;
        
        OrderManager instance = new OrderManager();
        
        OrderDTO expResult = null;
        
        OrderDTO result = instance.findOrder(OrderID);
        
        assertEquals(expResult, result);
    }
}
