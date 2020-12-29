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
        System.out.println("___ findTransaction");
        
        int TransactionID = 1;
        
        TransactionGateway instance = new TransactionGateway();
        
        String expResult = "Parcel collected";
        TransactionDTO result = instance.find(TransactionID);
        
        assertEquals( expResult, result.getName() );
    }

    @Test
    public void testFindOrderTransactions() {
        System.out.println("___ findOrderTransactions");
        
        int OrderID = 1;
        
        TransactionGateway instance = new TransactionGateway();
        
        ArrayList<TransactionDTO> result = instance.findOrderTransactions(OrderID);
        
        int countTransactions = result.size();
        
        assertTrue( countTransactions > 0 ); // There is at least one valid transaction
    }
}
