package Recipient_UI;

import dto.OrderDTO;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewRecipientOrdersCommandTest {
    
    public ViewRecipientOrdersCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("___ RecipientViewRecipientDeliveries execute");
        
        ViewRecipientOrdersCommand instance = new ViewRecipientOrdersCommand(2);
        
        Object result = instance.execute();
        
        // Perform checks
        boolean passed = true;
        
        /*
        System.out.println( ((ArrayList<OrderDTO>)result).get(0).getId() );
        System.out.println( ((ArrayList<OrderDTO>)result).get(0).getRecipient().getUsername() );
        System.out.println( ((ArrayList<OrderDTO>)result).get(0).getSeller().getUsername() );
        */
        
        if (
            ((ArrayList<OrderDTO>)result).size() < 1 || /* There should be at least one order found against this user */
            ((ArrayList<OrderDTO>)result).get(0).getId() != 2 ||
            !((ArrayList<OrderDTO>)result).get(0).getRecipient().getUsername().equalsIgnoreCase("driver") || 
            !((ArrayList<OrderDTO>)result).get(0).getSeller().getUsername().equalsIgnoreCase("recipient")
        ) {
           passed = false; 
        }
        
        assertTrue(passed);
    }
}
