package gateway;

import dto.ParcelDTO;
import java.sql.Date;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParcelGatewayTest {
    
    public ParcelGatewayTest() {
    }

    @Test
    public void testGetDate() {
        System.out.println("getDate");

        ParcelGateway instance = new ParcelGateway();
        
        java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        
        Date expResult = sqlDate;
        Date result = instance.getDate();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testFind() {
        System.out.println("find");
        
        int ParcelID = 2;
        
        ParcelGateway instance = new ParcelGateway();
        
        String expResult = "Item Two";
        ParcelDTO result = instance.find(ParcelID);
        
        assertEquals( expResult, result.getName() );
    }

    @Test
    public void testFindAllParcels() {
        System.out.println("findAllParcels");
        
        ParcelGateway instance = new ParcelGateway();
        
        ArrayList<ParcelDTO> result = instance.findAllParcels();
        
        int countParcels = result.size();
        
        assertTrue( countParcels > 0 ); // There is at least 1 valid parcel
    }
    
    /*
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
    */
}
