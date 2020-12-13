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
public class MetricDTOTest {
    
    public MetricDTOTest() {
    }

    /**
     * Test of hashCode method, of class MetricDTO.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        MetricDTO instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class MetricDTO.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        MetricDTO instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class MetricDTO.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        MetricDTO instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDeliveryCount method, of class MetricDTO.
     */
    @Test
    public void testGetDeliveryCount() {
        System.out.println("getDeliveryCount");
        MetricDTO instance = null;
        int expResult = 0;
        int result = instance.getDeliveryCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDaysToComplete method, of class MetricDTO.
     */
    @Test
    public void testGetDaysToComplete() {
        System.out.println("getDaysToComplete");
        MetricDTO instance = null;
        int expResult = 0;
        int result = instance.getDaysToComplete();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
