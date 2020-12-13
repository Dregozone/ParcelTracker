/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seller_UI;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class SellerCommandTest {
    
    public SellerCommandTest() {
    }

    /**
     * Test of execute method, of class SellerCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        SellerCommand instance = new SellerCommandImpl();
        Object expResult = null;
        Object result = instance.execute();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class SellerCommandImpl implements SellerCommand {

        public Object execute() {
            return null;
        }
    }
    
}
