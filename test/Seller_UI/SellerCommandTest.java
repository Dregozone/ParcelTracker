package Seller_UI;

import org.junit.Test;
import static org.junit.Assert.*;

public class SellerCommandTest {
    
    public SellerCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("__ SellerCommand execute");
        
        SellerCommand instance = new SellerCommandImpl();
        
        Object expResult = null;
        Object result = instance.execute();
        
        assertEquals(expResult, result);
    }

    public class SellerCommandImpl implements SellerCommand {

        public Object execute() {
            return null;
        }
    }
}
