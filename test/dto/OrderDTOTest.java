/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class OrderDTOTest {
    
    public OrderDTOTest() {
    }

    /**
     * Test of hashCode method, of class OrderDTO.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        OrderDTO instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class OrderDTO.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        OrderDTO instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class OrderDTO.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        OrderDTO instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecipient method, of class OrderDTO.
     */
    @Test
    public void testGetRecipient() {
        System.out.println("getRecipient");
        OrderDTO instance = null;
        UserDTO expResult = null;
        UserDTO result = instance.getRecipient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDriver method, of class OrderDTO.
     */
    @Test
    public void testGetDriver() {
        System.out.println("getDriver");
        OrderDTO instance = null;
        UserDTO expResult = null;
        UserDTO result = instance.getDriver();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSeller method, of class OrderDTO.
     */
    @Test
    public void testGetSeller() {
        System.out.println("getSeller");
        OrderDTO instance = null;
        UserDTO expResult = null;
        UserDTO result = instance.getSeller();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateAdded method, of class OrderDTO.
     */
    @Test
    public void testGetDateAdded() {
        System.out.println("getDateAdded");
        OrderDTO instance = null;
        String expResult = "";
        String result = instance.getDateAdded();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isIsComplete method, of class OrderDTO.
     */
    @Test
    public void testIsIsComplete() {
        System.out.println("isIsComplete");
        OrderDTO instance = null;
        boolean expResult = false;
        boolean result = instance.isIsComplete();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateCompleted method, of class OrderDTO.
     */
    @Test
    public void testGetDateCompleted() {
        System.out.println("getDateCompleted");
        OrderDTO instance = null;
        String expResult = "";
        String result = instance.getDateCompleted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
