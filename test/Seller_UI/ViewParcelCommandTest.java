package Seller_UI;

import dto.ParcelDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewParcelCommandTest {
    
    public ViewParcelCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("___ SellerViewParcelCommand execute");
        
        int parcelId = 2;
        
        ViewParcelCommand instance = new ViewParcelCommand(parcelId);
        
        String expResult = "Item Two";
        Object result = instance.execute();
        
        ParcelDTO parcel = (ParcelDTO)result;
        
        assertEquals( expResult, parcel.getName() );
    }    
}
