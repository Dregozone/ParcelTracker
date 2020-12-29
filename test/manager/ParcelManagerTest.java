package manager;

import dto.ParcelDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParcelManagerTest {
    
    public ParcelManagerTest() {
    }

    @Test
    public void testFindParcel() {
        System.out.println("__ findParcel");
        
        int ParcelID = 2;
        
        ParcelManager instance = new ParcelManager();
        
        String expResult = "Item Two";
        ParcelDTO result = instance.findParcel(ParcelID);
        
        assertEquals( expResult, result.getName() );
    }
}
