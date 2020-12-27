package Driver_UI;

import dto.TransactionDTO;
import dto.UserDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class RemoveTransactionCommandTest {
    
    public RemoveTransactionCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("__ DriverRemoveTransactionCommand execute");
        
        UserDTO addedBy = new UserDTO(2, "a", "a", "a", "a", "1900-01-01", "1900-01-01", "a", "a", "a", "a", "a", "a", true, "Driver");
        TransactionDTO transaction = new TransactionDTO(100, 2, "Picked up", addedBy, "1900-01-01");
        
        RemoveTransactionCommand instance = new RemoveTransactionCommand(transaction);
        
        Object expResult = null;
        Object result = instance.execute();
        
        assertEquals(expResult, result);
    }
}
