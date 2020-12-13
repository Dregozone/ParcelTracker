/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Register_UI;

import dto.UserDTO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class RegisterCommandFactoryTest {
    
    public RegisterCommandFactoryTest() {
    }

    /**
     * Test of createCommand method, of class RegisterCommandFactory.
     */
    @Test
    public void testCreateCommand() {
        System.out.println("createCommand");
        int commandType = 0;
        UserDTO userDTO = null;
        RegisterCommand expResult = null;
        RegisterCommand result = RegisterCommandFactory.createCommand(commandType, userDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
