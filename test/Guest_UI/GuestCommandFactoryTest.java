/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guest_UI;

import dto.UserDTO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class GuestCommandFactoryTest {
    
    public GuestCommandFactoryTest() {
    }

    /**
     * Test of createCommand method, of class GuestCommandFactory.
     */
    @Test
    public void testCreateCommand_int_String() {
        System.out.println("createCommand");
        int commandType = 0;
        String username = "";
        GuestCommand expResult = null;
        GuestCommand result = GuestCommandFactory.createCommand(commandType, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCommand method, of class GuestCommandFactory.
     */
    @Test
    public void testCreateCommand_int_UserDTO() {
        System.out.println("createCommand");
        int commandType = 0;
        UserDTO userDTO = null;
        GuestCommand expResult = null;
        GuestCommand result = GuestCommandFactory.createCommand(commandType, userDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
