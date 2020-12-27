package gateway;

import dto.TransactionDTO;
import java.sql.Date;
import java.util.ArrayList;
import static junit.framework.Assert.*;
import org.junit.Test;

public class TransactionGatewayTest {
    
    public TransactionGatewayTest() {
    }

    @Test
    public void testFind() {
        System.out.println("find");
        
        int TransactionID = 1;
        
        TransactionGateway instance = new TransactionGateway();
        
        String expResult = "Parcel collected";
        TransactionDTO result = instance.find(TransactionID);
        
        assertEquals( expResult, result.getName() );
    }

    @Test
    public void testFindOrderTransactions() {
        System.out.println("findOrderTransactions");
        
        int OrderID = 1;
        
        TransactionGateway instance = new TransactionGateway();
        
        ArrayList<TransactionDTO> result = instance.findOrderTransactions(OrderID);
        
        int countTransactions = result.size();
        
        assertTrue( countTransactions > 0 ); // There is at least one valid transaction
    }
    
    /*
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        TransactionGateway instance = new TransactionGateway();
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testInsertTransaction() {
        System.out.println("insertTransaction");
        TransactionDTO transaction = null;
        TransactionGateway instance = new TransactionGateway();
        boolean expResult = false;
        boolean result = instance.insertTransaction(transaction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteTransaction() {
        System.out.println("deleteTransaction");
        TransactionDTO transaction = null;
        TransactionGateway instance = new TransactionGateway();
        boolean expResult = false;
        boolean result = instance.deleteTransaction(transaction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
