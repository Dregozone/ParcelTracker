package Seller_UI;

import dto.ParcelDTO;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewOrderParcelsCommandTest {
    
    public ViewOrderParcelsCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("___ SellerViewOrderParcelsCommand execute");

        int orderId = 2;
        
        ViewOrderParcelsCommand instance = new ViewOrderParcelsCommand(orderId);
        
        String expResult = "Item Two";
        Object results = instance.execute();

        ArrayList<ParcelDTO> parcels = (ArrayList<ParcelDTO>)results;
        
        ParcelDTO parcel = (ParcelDTO)( parcels.get(0) );
        
        assertEquals( expResult, parcel.getName() );
    }
}
