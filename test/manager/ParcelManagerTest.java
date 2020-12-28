package manager;

import dto.ParcelDTO;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParcelManagerTest {
    
    public ParcelManagerTest() {
    }

    @Test
    public void testFindParcel() {
        System.out.println("findParcel");
        
        int ParcelID = 2;
        
        ParcelManager instance = new ParcelManager();
        
        String expResult = "Item Two";
        ParcelDTO result = instance.findParcel(ParcelID);
        
        assertEquals( expResult, result.getName() );
    }
    
    /*
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
    */
}
