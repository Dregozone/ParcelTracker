/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Driver_UI;

import dto.TransactionDTO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class DriverCommandFactoryTest {
    
    public DriverCommandFactoryTest() {
    }

    /**
     * Test of createCommand method, of class DriverCommandFactory.
     */
    @Test
    public void testCreateCommand_int() {
        System.out.println("createCommand");
        int commandType = 0;
        DriverCommand expResult = null;
        DriverCommand result = DriverCommandFactory.createCommand(commandType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCommand method, of class DriverCommandFactory.
     */
    @Test
    public void testCreateCommand_int_TransactionDTO() {
        System.out.println("createCommand");
        int commandType = 0;
        TransactionDTO transactionDTO = null;
        DriverCommand expResult = null;
        DriverCommand result = DriverCommandFactory.createCommand(commandType, transactionDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
