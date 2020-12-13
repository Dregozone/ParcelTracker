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
public class ParcelDTOTest {
    
    public ParcelDTOTest() {
    }

    /**
     * Test of hashCode method, of class ParcelDTO.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ParcelDTO instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class ParcelDTO.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        ParcelDTO instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class ParcelDTO.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ParcelDTO instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class ParcelDTO.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        ParcelDTO instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class ParcelDTO.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        ParcelDTO instance = null;
        String expResult = "";
        String result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeightGrams method, of class ParcelDTO.
     */
    @Test
    public void testGetWeightGrams() {
        System.out.println("getWeightGrams");
        ParcelDTO instance = null;
        int expResult = 0;
        int result = instance.getWeightGrams();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSeller method, of class ParcelDTO.
     */
    @Test
    public void testGetSeller() {
        System.out.println("getSeller");
        ParcelDTO instance = null;
        UserDTO expResult = null;
        UserDTO result = instance.getSeller();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateAdded method, of class ParcelDTO.
     */
    @Test
    public void testGetDateAdded() {
        System.out.println("getDateAdded");
        ParcelDTO instance = null;
        String expResult = "";
        String result = instance.getDateAdded();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateModified method, of class ParcelDTO.
     */
    @Test
    public void testGetDateModified() {
        System.out.println("getDateModified");
        ParcelDTO instance = null;
        String expResult = "";
        String result = instance.getDateModified();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimesSold method, of class ParcelDTO.
     */
    @Test
    public void testGetTimesSold() {
        System.out.println("getTimesSold");
        ParcelDTO instance = null;
        int expResult = 0;
        int result = instance.getTimesSold();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantityInOrder method, of class ParcelDTO.
     */
    @Test
    public void testGetQuantityInOrder() {
        System.out.println("getQuantityInOrder");
        ParcelDTO instance = null;
        int expResult = 0;
        int result = instance.getQuantityInOrder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuantityInOrder method, of class ParcelDTO.
     */
    @Test
    public void testSetQuantityInOrder() {
        System.out.println("setQuantityInOrder");
        int quantityInOrder = 0;
        ParcelDTO instance = null;
        instance.setQuantityInOrder(quantityInOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
