package Seller_UI;

import org.junit.Test;
import static org.junit.Assert.*;

public class EditParcelCommandTest {
    
    public EditParcelCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("execute");
        EditParcelCommand instance = null;
        Object expResult = null;
        Object result = instance.execute();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
