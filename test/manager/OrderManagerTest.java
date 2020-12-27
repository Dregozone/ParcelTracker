package manager;

import dto.MetricDTO;
import dto.OrderDTO;
import dto.ParcelDTO;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderManagerTest {
    
    public OrderManagerTest() {
    }

    @Test
    public void testFindOrder() {
        System.out.println("findOrder");
        
        int OrderID = 0;
        
        OrderManager instance = new OrderManager();
        
        OrderDTO expResult = null;
        
        OrderDTO result = instance.findOrder(OrderID);
        
        assertEquals(expResult, result);
    }
    
    /*
    @Test
    public void testCreateOrder() {
        System.out.println("createOrder");
        OrderDTO order = null;
        OrderManager instance = new OrderManager();
        boolean expResult = false;
        boolean result = instance.createOrder(order);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testEditOrder() {
        System.out.println("editOrder");
        OrderDTO order = null;
        OrderManager instance = new OrderManager();
        boolean expResult = false;
        boolean result = instance.editOrder(order);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteOrder() {
        System.out.println("deleteOrder");
        int orderId = 0;
        OrderManager instance = new OrderManager();
        boolean expResult = false;
        boolean result = instance.deleteOrder(orderId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testViewRecipientOrders() {
        System.out.println("viewRecipientOrders");
        int UserID = 0;
        OrderManager instance = new OrderManager();
        ArrayList<OrderDTO> expResult = null;
        ArrayList<OrderDTO> result = instance.viewRecipientOrders(UserID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testViewDriverMetrics() {
        System.out.println("viewDriverMetrics");
        OrderManager instance = new OrderManager();
        ArrayList<MetricDTO> expResult = null;
        ArrayList<MetricDTO> result = instance.viewDriverMetrics();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testViewOrderParcels() {
        System.out.println("viewOrderParcels");
        int OrderID = 0;
        OrderManager instance = new OrderManager();
        ArrayList<ParcelDTO> expResult = null;
        ArrayList<ParcelDTO> result = instance.viewOrderParcels(OrderID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testViewAllOrders() {
        System.out.println("viewAllOrders");
        OrderManager instance = new OrderManager();
        ArrayList<OrderDTO> expResult = null;
        ArrayList<OrderDTO> result = instance.viewAllOrders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
