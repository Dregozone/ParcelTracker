package manager;

import dto.TransactionDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class TransactionManagerTest {
    
    public TransactionManagerTest() {
    }

    @Test
    public void testFindTransaction() {
        System.out.println("___ findTransaction");
        
        int TransactionID = 1;
        
        TransactionManager instance = new TransactionManager();
        
        String expResult = "Parcel collected";
        TransactionDTO result = instance.findTransaction(TransactionID);
        
        assertEquals( expResult, result.getName() );
    }
}
