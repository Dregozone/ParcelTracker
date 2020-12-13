/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.TransactionDTO;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aclea
 */
public class TransactionManagerIT {
    
    public TransactionManagerIT() {
    }

    /**
     * Test of addTransaction method, of class TransactionManager.
     */
    @Test
    public void testAddTransaction() {
        System.out.println("addTransaction");
        TransactionDTO transaction = null;
        TransactionManager instance = new TransactionManager();
        boolean expResult = false;
        boolean result = instance.addTransaction(transaction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTransaction method, of class TransactionManager.
     */
    @Test
    public void testRemoveTransaction() {
        System.out.println("removeTransaction");
        TransactionDTO transaction = null;
        TransactionManager instance = new TransactionManager();
        boolean expResult = false;
        boolean result = instance.removeTransaction(transaction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findTransaction method, of class TransactionManager.
     */
    @Test
    public void testFindTransaction() {
        System.out.println("findTransaction");
        int TransactionID = 0;
        TransactionManager instance = new TransactionManager();
        TransactionDTO expResult = null;
        TransactionDTO result = instance.findTransaction(TransactionID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewOrderTransactions method, of class TransactionManager.
     */
    @Test
    public void testViewOrderTransactions() {
        System.out.println("viewOrderTransactions");
        int OrderID = 0;
        TransactionManager instance = new TransactionManager();
        ArrayList<TransactionDTO> expResult = null;
        ArrayList<TransactionDTO> result = instance.viewOrderTransactions(OrderID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
