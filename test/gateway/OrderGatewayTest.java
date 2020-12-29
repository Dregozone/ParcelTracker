package gateway;

import dto.MetricDTO;
import dto.OrderDTO;
import dto.ParcelDTO;
import java.sql.Date;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderGatewayTest {
    
    public OrderGatewayTest() {
    }

    @Test
    public void testGetDate() {
        System.out.println("___ getDate");
        
        OrderGateway instance = new OrderGateway();
        
        java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        
        Date expResult = sqlDate;
        Date result = instance.getDate();
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFind() {
        System.out.println("___ findOrder");

        int OrderID = 2;

        OrderGateway instance = new OrderGateway();
        
        OrderDTO result = instance.find(OrderID);
        
        boolean passed = true;
        
        if (
            !result.getRecipient().getUsername().equalsIgnoreCase("driver") ||
            !result.getSeller().getUsername().equalsIgnoreCase("recipient")
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }
    
    @Test
    public void testFindAllOrders() {
        System.out.println("___ findAllOrders");
        
        OrderGateway instance = new OrderGateway();
        
        ArrayList<OrderDTO> result = instance.findAllOrders();
        
        int countOrders = result.size();
        
        assertTrue(countOrders > 0); // There is at least 1 valid order
    }
}
