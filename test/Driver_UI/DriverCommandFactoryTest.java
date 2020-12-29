package Driver_UI;

import dto.TransactionDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class DriverCommandFactoryTest {
    
    public DriverCommandFactoryTest() {
    }

    @Test
    public void testCreateCommand_int() {
        System.out.println("___ DriverCommandFactoryCreateCommand");
        
        int commandType = 0;
        
        DriverCommand expResult = null;
        DriverCommand result = DriverCommandFactory.createCommand(commandType);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateCommand_int_TransactionDTO() {
        System.out.println("___ Driver createCommand");
        
        int commandType = 0;
        
        TransactionDTO transactionDTO = null;
        
        DriverCommand expResult = null;
        DriverCommand result = DriverCommandFactory.createCommand(commandType, transactionDTO);
        
        assertEquals(expResult, result);
    }
    
}
