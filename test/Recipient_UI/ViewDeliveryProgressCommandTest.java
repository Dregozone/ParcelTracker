package Recipient_UI;

import org.junit.Test;
import static org.junit.Assert.*;

public class ViewDeliveryProgressCommandTest {
    
    public ViewDeliveryProgressCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("__ RecipientViewDeliveryProgress execute");
        
        ViewOrderProgressCommand instance = null;
        
        Object expResult = null;
        Object result = instance.execute();
        
        System.out.println( result );
        
        assertEquals(expResult, result);
    }
}
