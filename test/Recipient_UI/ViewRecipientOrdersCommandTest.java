package Recipient_UI;

import org.junit.Test;
import static org.junit.Assert.*;

public class ViewRecipientOrdersCommandTest {
    
    public ViewRecipientOrdersCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("__ RecipientViewRecipientDeliveries execute");
        
        ViewRecipientOrdersCommand instance = null;
        
        Object expResult = null;
        Object result = instance.execute();
        
        assertEquals(expResult, result);
    }
}
