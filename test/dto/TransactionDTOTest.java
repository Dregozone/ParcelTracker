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
public class TransactionDTOTest {
    
    public TransactionDTOTest() {
    }

    /**
     * Test of hashCode method, of class TransactionDTO.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        TransactionDTO instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class TransactionDTO.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        TransactionDTO instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class TransactionDTO.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        TransactionDTO instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderId method, of class TransactionDTO.
     */
    @Test
    public void testGetOrderId() {
        System.out.println("getOrderId");
        TransactionDTO instance = null;
        int expResult = 0;
        int result = instance.getOrderId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class TransactionDTO.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        TransactionDTO instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddedBy method, of class TransactionDTO.
     */
    @Test
    public void testGetAddedBy() {
        System.out.println("getAddedBy");
        TransactionDTO instance = null;
        UserDTO expResult = null;
        UserDTO result = instance.getAddedBy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateAdded method, of class TransactionDTO.
     */
    @Test
    public void testGetDateAdded() {
        System.out.println("getDateAdded");
        TransactionDTO instance = null;
        String expResult = "";
        String result = instance.getDateAdded();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
