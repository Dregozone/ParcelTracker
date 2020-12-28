package Seller_UI;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeleteOrderCommandTest {
    
    public DeleteOrderCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("__ SellerDeleteOrderCommand execute");

        DeleteOrderCommand instance = null;

        Object expResult = null;
        Object result = instance.execute();

        assertEquals(expResult, result);
    }
}
