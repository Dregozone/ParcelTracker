/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Register_UI;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class RegisterCommandTest {
    
    public RegisterCommandTest() {
    }

    /**
     * Test of execute method, of class RegisterCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        RegisterCommand instance = new RegisterCommandImpl();
        Object expResult = null;
        Object result = instance.execute();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class RegisterCommandImpl implements RegisterCommand {

        public Object execute() {
            return null;
        }
    }
    
}
