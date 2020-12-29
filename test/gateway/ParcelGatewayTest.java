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
        System.out.println("___ getDate");

        ParcelGateway instance = new ParcelGateway();
        
        java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        
        Date expResult = sqlDate;
        Date result = instance.getDate();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testFind() {
        System.out.println("___ findParcel");
        
        int ParcelID = 2;
        
        ParcelGateway instance = new ParcelGateway();
        
        String expResult = "Item Two";
        ParcelDTO result = instance.find(ParcelID);
        
        assertEquals( expResult, result.getName() );
    }

    @Test
    public void testFindAllParcels() {
        System.out.println("___ findAllParcels");
        
        ParcelGateway instance = new ParcelGateway();
        
        ArrayList<ParcelDTO> result = instance.findAllParcels();
        
        int countParcels = result.size();
        
        assertTrue( countParcels > 0 ); // There is at least 1 valid parcel
    }
}
