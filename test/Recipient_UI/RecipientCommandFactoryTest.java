package Recipient_UI;

import org.junit.Test;
import static org.junit.Assert.*;

public class RecipientCommandFactoryTest {
    
    public RecipientCommandFactoryTest() {
    }

    @Test
    public void testCreateCommand() {
        System.out.println("__ RecipientCommandFactoryCreateCommand");
        
        int commandType = 0;
        int id = 0;
        
        RecipientCommand expResult = null;
        RecipientCommand result = RecipientCommandFactory.createCommand(commandType, id);
        
        assertEquals(expResult, result);
    }
}
