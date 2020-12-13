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
public class ViewUserRoleCommandTest {
    
    public ViewUserRoleCommandTest() {
    }

    /**
     * Test of execute method, of class ViewUserRoleCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        ViewUserRoleCommand instance = null;
        String expResult = "";
        String result = instance.execute();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
