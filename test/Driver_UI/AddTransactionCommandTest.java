package Driver_UI;

import dto.TransactionDTO;
import dto.UserDTO;
import managedbean.DriverBean;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddTransactionCommandTest {
    
    public AddTransactionCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("__ DriverAddTransactionCommand execute");
        
        DriverBean driverInstance = new DriverBean();
        int nextTransactionId = driverInstance.getNextTransactionId();
                
        UserDTO addedBy = new UserDTO(2, "a", "a", "a", "a", "1900-01-01", "1900-01-01", "a", "a", "a", "a", "a", "a", true, "Driver");
        TransactionDTO transaction = new TransactionDTO(nextTransactionId, 2, "Picked up", addedBy, "1900-01-01");
        
        AddTransactionCommand instance = new AddTransactionCommand( transaction );
        
        Object expResult = null;
        Object result = instance.execute();
        
        assertEquals(expResult, result);
    }
}
