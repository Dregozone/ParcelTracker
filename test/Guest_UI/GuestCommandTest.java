/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guest_UI;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class GuestCommandTest {
    
    public GuestCommandTest() {
    }

    /**
     * Test of execute method, of class GuestCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        GuestCommand instance = new GuestCommandImpl();
        Object expResult = null;
        Object result = instance.execute();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class GuestCommandImpl implements GuestCommand {

        public Object execute() {
            return null;
        }
    }
    
}
