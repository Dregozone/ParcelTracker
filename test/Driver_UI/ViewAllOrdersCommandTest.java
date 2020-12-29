package Driver_UI;

import dto.OrderDTO;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewAllOrdersCommandTest {
    
    public ViewAllOrdersCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("___ DriverViewAllOrdersCommand execute");
        
        ViewAllOrdersCommand instance = new ViewAllOrdersCommand();
        
        Object expResult = null;
        Object result = instance.execute();
        
        int countOrders = ((ArrayList<OrderDTO>)result).size();
        
        assertTrue(countOrders > 0); // There is at lease one valid order found
    }
}
