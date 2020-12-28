package Seller_UI;

import dto.ParcelDTO;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewAllParcelsCommandTest {
    
    public ViewAllParcelsCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("__ SellerViewAllParcels execute");

        ViewAllParcelsCommand instance = new ViewAllParcelsCommand();

        Object result = instance.execute();

        int countParcels = ((ArrayList<ParcelDTO>)result).size();
        
        assertTrue( countParcels > 0 ); // There is at least 1 valid parcel
    }
}
