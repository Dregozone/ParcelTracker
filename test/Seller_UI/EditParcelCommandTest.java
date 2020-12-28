package Seller_UI;

import dto.ParcelDTO;
import dto.UserDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class EditParcelCommandTest {
    
    public EditParcelCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("__ SellerEditParcelCommand execute");
        
        UserDTO seller = new UserDTO(1, "first", "last", "recipient", "123", "1900-01-01", "1900-01-01", "a", "a", "a", "a", "a", "a", true, "Recipient");
        ParcelDTO parcel = new ParcelDTO(1, "name", "type", 10, seller, "1900-01-01", "1900-01-01", 0);
        
        EditParcelCommand instance = new EditParcelCommand(parcel);
        
        String expResult = "name";
        Object result = instance.execute();
        
        ParcelDTO parcelDetails = (ParcelDTO)result;
        
        assertEquals( expResult, parcelDetails.getName() );
    }
}
