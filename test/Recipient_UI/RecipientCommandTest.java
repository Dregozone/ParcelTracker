package Recipient_UI;

import org.junit.Test;
import static org.junit.Assert.*;

public class RecipientCommandTest {
    
    public RecipientCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("___ RecipientCommandTest execute");
        
        RecipientCommand instance = new RecipientCommandImpl();
        
        Object expResult = null;
        Object result = instance.execute();
        
        assertEquals(expResult, result);
    }

    public class RecipientCommandImpl implements RecipientCommand {

        public Object execute() {
            return null;
        }
    }
}
