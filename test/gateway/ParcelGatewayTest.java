/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gateway;

import dto.ParcelDTO;
import java.sql.Date;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class ParcelGatewayTest {
    
    public ParcelGatewayTest() {
    }

    /**
     * Test of getDate method, of class ParcelGateway.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        ParcelGateway instance = new ParcelGateway();
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertParcel method, of class ParcelGateway.
     */
    @Test
    public void testInsertParcel() {
        System.out.println("insertParcel");
        ParcelDTO parcel = null;
        ParcelGateway instance = new ParcelGateway();
        boolean expResult = false;
        boolean result = instance.insertParcel(parcel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateParcel method, of class ParcelGateway.
     */
    @Test
    public void testUpdateParcel() {
        System.out.println("updateParcel");
        ParcelDTO parcel = null;
        ParcelGateway instance = new ParcelGateway();
        boolean expResult = false;
        boolean result = instance.updateParcel(parcel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteParcel method, of class ParcelGateway.
     */
    @Test
    public void testDeleteParcel() {
        System.out.println("deleteParcel");
        int parcelId = 0;
        ParcelGateway instance = new ParcelGateway();
        boolean expResult = false;
        boolean result = instance.deleteParcel(parcelId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class ParcelGateway.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        int ParcelID = 0;
        ParcelGateway instance = new ParcelGateway();
        ParcelDTO expResult = null;
        ParcelDTO result = instance.find(ParcelID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllParcels method, of class ParcelGateway.
     */
    @Test
    public void testFindAllParcels() {
        System.out.println("findAllParcels");
        ParcelGateway instance = new ParcelGateway();
        ArrayList<ParcelDTO> expResult = null;
        ArrayList<ParcelDTO> result = instance.findAllParcels();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findOrderParcels method, of class ParcelGateway.
     */
    @Test
    public void testFindOrderParcels() {
        System.out.println("findOrderParcels");
        int OrderID = 0;
        ParcelGateway instance = new ParcelGateway();
        ArrayList<ParcelDTO> expResult = null;
        ArrayList<ParcelDTO> result = instance.findOrderParcels(OrderID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
