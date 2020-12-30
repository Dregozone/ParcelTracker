package Seller_UI;

import dto.ParcelDTO;
import dto.UserDTO;
import managedbean.SellerBean;
import org.junit.Test;
import static org.junit.Assert.*;

public class CreateParcelCommandTest {
    
    public CreateParcelCommandTest() {
    }
 
    @Test
    public void testExecute() {
        System.out.println("___ SellerCreateParcelCommand execute");

        SellerBean sellerInstance = new SellerBean();
        UserDTO seller = new UserDTO(3, "a", "a", "seller", "123", "1900-01-01", "1900-01-01", "a", "a", "a", "a", "a", "a", true, "Seller");
        ParcelDTO parcel = new ParcelDTO(sellerInstance.getNextParcelId(), "name", "type", 30, seller, "1900-01-01", "1900-01-01", 2);
        
        CreateParcelCommand instance = new CreateParcelCommand(parcel);

        Object expResult = "name";
        Object result = instance.execute();
        
        ParcelDTO parcelDetails = (ParcelDTO)result;

        assertEquals( expResult, parcelDetails.getName() );
    }
}
