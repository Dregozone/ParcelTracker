package Seller_UI;

import dto.ParcelDTO;
import dto.UserDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeleteParcelCommandTest {
    
    public DeleteParcelCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("__ SellerDeleteParcelCommand execute");
        
        // Prep temp parcel to be created for deletion
        int parcelId = 300;
        UserDTO seller = new UserDTO(3, "a", "a", "seller", "a", "1900-01-01", "1900-01-01", "a", "a", "a", "a", "a", "a", true, "Seller");
        ParcelDTO parcel = new ParcelDTO(parcelId, "name", "type", 15, seller, "1900-01-01", "1900-01-01", 1);
        CreateParcelCommand createInstance = new CreateParcelCommand(parcel);
        
        // Create temp parcel
        createInstance.execute();
        
        // Delete the parcel
        DeleteParcelCommand instance = new DeleteParcelCommand(parcelId);
        
        Object expResult = null;
        Object result = instance.execute();
        
        assertEquals(expResult, result);
    }
}
