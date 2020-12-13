/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login_UI;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class LoginCommandFactoryTest {
    
    public LoginCommandFactoryTest() {
    }

    /**
     * Test of createCommand method, of class LoginCommandFactory.
     */
    @Test
    public void testCreateCommand() {
        System.out.println("createCommand");
        int commandType = 0;
        String username = "";
        LoginCommand expResult = null;
        LoginCommand result = LoginCommandFactory.createCommand(commandType, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
