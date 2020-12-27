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
        System.out.println("__ getDate");
        
        OrderGateway instance = new OrderGateway();
        
        java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        
        Date expResult = sqlDate;
        Date result = instance.getDate();
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFind() {
        System.out.println("find");

        int OrderID = 1;

        OrderGateway instance = new OrderGateway();
        
        OrderDTO result = instance.find(OrderID);
        
        boolean passed = true;
        
        if (
            !result.getRecipient().getUsername().equalsIgnoreCase("recipient") ||
            !result.getSeller().getUsername().equalsIgnoreCase("recipient")
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }
    
    @Test
    public void testFindAllOrders() {
        System.out.println("findAllOrders");
        
        OrderGateway instance = new OrderGateway();
        
        ArrayList<OrderDTO> result = instance.findAllOrders();
        
        int countOrders = result.size();
        
        assertTrue(countOrders > 0); // There is at least 1 valid order
    }

    /*
    @Test
    public void testInsertOrder() {
        System.out.println("insertOrder");
        OrderDTO order = null;
        OrderGateway instance = new OrderGateway();
        boolean expResult = false;
        boolean result = instance.insertOrder(order);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateOrder() {
        System.out.println("updateOrder");
        OrderDTO order = null;
        OrderGateway instance = new OrderGateway();
        boolean expResult = false;
        boolean result = instance.updateOrder(order);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteOrder() {
        System.out.println("deleteOrder");
        int orderId = 0;
        OrderGateway instance = new OrderGateway();
        boolean expResult = false;
        boolean result = instance.deleteOrder(orderId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindRecipientOrders() {
        System.out.println("findRecipientOrders");
        int UserID = 0;
        OrderGateway instance = new OrderGateway();
        ArrayList<OrderDTO> expResult = null;
        ArrayList<OrderDTO> result = instance.findRecipientOrders(UserID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindDriverMetrics() {
        System.out.println("findDriverMetrics");
        OrderGateway instance = new OrderGateway();
        ArrayList<MetricDTO> expResult = null;
        ArrayList<MetricDTO> result = instance.findDriverMetrics();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindOrderParcels() {
        System.out.println("findOrderParcels");
        int OrderID = 0;
        OrderGateway instance = new OrderGateway();
        ArrayList<ParcelDTO> expResult = null;
        ArrayList<ParcelDTO> result = instance.findOrderParcels(OrderID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
