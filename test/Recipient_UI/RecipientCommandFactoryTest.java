/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recipient_UI;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class RecipientCommandFactoryTest {
    
    public RecipientCommandFactoryTest() {
    }

    /**
     * Test of createCommand method, of class RecipientCommandFactory.
     */
    @Test
    public void testCreateCommand() {
        System.out.println("createCommand");
        int commandType = 0;
        int id = 0;
        RecipientCommand expResult = null;
        RecipientCommand result = RecipientCommandFactory.createCommand(commandType, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
