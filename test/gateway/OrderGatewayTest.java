/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gateway;

import dto.MetricDTO;
import dto.OrderDTO;
import dto.ParcelDTO;
import java.sql.Date;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class OrderGatewayTest {
    
    public OrderGatewayTest() {
    }

    /**
     * Test of getDate method, of class OrderGateway.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        OrderGateway instance = new OrderGateway();
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertOrder method, of class OrderGateway.
     */
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

    /**
     * Test of updateOrder method, of class OrderGateway.
     */
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

    /**
     * Test of deleteOrder method, of class OrderGateway.
     */
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

    /**
     * Test of find method, of class OrderGateway.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        int OrderID = 0;
        OrderGateway instance = new OrderGateway();
        OrderDTO expResult = null;
        OrderDTO result = instance.find(OrderID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findRecipientDeliveries method, of class OrderGateway.
     */
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

    /**
     * Test of findDriverMetrics method, of class OrderGateway.
     */
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

    /**
     * Test of findAllDeliveries method, of class OrderGateway.
     */
    @Test
    public void testFindAllOrders() {
        System.out.println("findAllOrders");
        OrderGateway instance = new OrderGateway();
        ArrayList<OrderDTO> expResult = null;
        ArrayList<OrderDTO> result = instance.findAllOrders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findOrderParcels method, of class OrderGateway.
     */
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
    
}
