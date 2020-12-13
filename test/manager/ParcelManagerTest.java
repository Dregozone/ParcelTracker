/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.ParcelDTO;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class ParcelManagerTest {
    
    public ParcelManagerTest() {
    }

    /**
     * Test of createParcel method, of class ParcelManager.
     */
    @Test
    public void testCreateParcel() {
        System.out.println("createParcel");
        ParcelDTO parcel = null;
        ParcelManager instance = new ParcelManager();
        boolean expResult = false;
        boolean result = instance.createParcel(parcel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editParcel method, of class ParcelManager.
     */
    @Test
    public void testEditParcel() {
        System.out.println("editParcel");
        ParcelDTO parcel = null;
        ParcelManager instance = new ParcelManager();
        boolean expResult = false;
        boolean result = instance.editParcel(parcel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteParcel method, of class ParcelManager.
     */
    @Test
    public void testDeleteParcel() {
        System.out.println("deleteParcel");
        int parcelId = 0;
        ParcelManager instance = new ParcelManager();
        boolean expResult = false;
        boolean result = instance.deleteParcel(parcelId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findParcel method, of class ParcelManager.
     */
    @Test
    public void testFindParcel() {
        System.out.println("findParcel");
        int ParcelID = 0;
        ParcelManager instance = new ParcelManager();
        ParcelDTO expResult = null;
        ParcelDTO result = instance.findParcel(ParcelID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewAllParcels method, of class ParcelManager.
     */
    @Test
    public void testViewAllParcels() {
        System.out.println("viewAllParcels");
        ParcelManager instance = new ParcelManager();
        ArrayList<ParcelDTO> expResult = null;
        ArrayList<ParcelDTO> result = instance.viewAllParcels();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
