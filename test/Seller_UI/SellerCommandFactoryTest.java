/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seller_UI;

import dto.OrderDTO;
import dto.ParcelDTO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class SellerCommandFactoryTest {
    
    public SellerCommandFactoryTest() {
    }

    /**
     * Test of createCommand method, of class SellerCommandFactory.
     */
    @Test
    public void testCreateCommand_int() {
        System.out.println("createCommand");
        int commandType = 0;
        SellerCommand expResult = null;
        SellerCommand result = SellerCommandFactory.createCommand(commandType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCommand method, of class SellerCommandFactory.
     */
    @Test
    public void testCreateCommand_int_int() {
        System.out.println("createCommand");
        int commandType = 0;
        int id = 0;
        SellerCommand expResult = null;
        SellerCommand result = SellerCommandFactory.createCommand(commandType, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCommand method, of class SellerCommandFactory.
     */
    @Test
    public void testCreateCommand_int_OrderDTO() {
        System.out.println("createCommand");
        int commandType = 0;
        OrderDTO orderDTO = null;
        SellerCommand expResult = null;
        SellerCommand result = SellerCommandFactory.createCommand(commandType, orderDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCommand method, of class SellerCommandFactory.
     */
    @Test
    public void testCreateCommand_int_ParcelDTO() {
        System.out.println("createCommand");
        int commandType = 0;
        ParcelDTO parcelDTO = null;
        SellerCommand expResult = null;
        SellerCommand result = SellerCommandFactory.createCommand(commandType, parcelDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
