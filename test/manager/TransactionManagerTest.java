package manager;

import dto.TransactionDTO;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class TransactionManagerTest {
    
    public TransactionManagerTest() {
    }

    @Test
    public void testFindTransaction() {
        System.out.println("__ findTransaction");
        
        int TransactionID = 1;
        
        TransactionManager instance = new TransactionManager();
        
        String expResult = "Parcel collected";
        TransactionDTO result = instance.findTransaction(TransactionID);
        
        assertEquals( expResult, result.getName() );
    }
    
    /*
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
    */
}
