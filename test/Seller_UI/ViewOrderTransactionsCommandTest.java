package Seller_UI;

import dto.TransactionDTO;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewOrderTransactionsCommandTest {
    
    public ViewOrderTransactionsCommandTest() {
    }

    @Test
    public void testExecute() {
        System.out.println("___ SellerViewOrderTransactionsCommand execute");
        
        int orderId = 1;
        
        ViewOrderTransactionsCommand instance = new ViewOrderTransactionsCommand(orderId);
        
        Object expResult = "Parcel collected";
        Object results = instance.execute();
        
        ArrayList<TransactionDTO> transactions = (ArrayList<TransactionDTO>)results;
        
        TransactionDTO transaction = (TransactionDTO)( transactions.get(0) );
        
        assertEquals( expResult, transaction.getName() );
    }    
}
