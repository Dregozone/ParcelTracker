package Recipient_UI;

import dto.OrderDTO;
import static java.lang.System.out;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewOrderProgressCommandTest {
    
    public ViewOrderProgressCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("__ RecipientViewDeliveryProgress execute");
        
        ViewOrderProgressCommand instance = new ViewOrderProgressCommand(2);
        
        // Find order #1 progress
        Object result = instance.execute();
        OrderDTO order = (OrderDTO)result;
        
        /*
        System.out.println( order.getId() );
        System.out.println( order.getRecipient().getUsername() );
        System.out.println( order.getSeller().getUsername() );
        */
        
        // Perform checks
        boolean passed = true;
        
        if (
            order.getId() != 2 ||
            !order.getRecipient().getUsername().equalsIgnoreCase("driver") ||
            !order.getSeller().getUsername().equalsIgnoreCase("recipient")
        ) {
            passed = false;
        }
        
        assertTrue(passed);
    }
}
