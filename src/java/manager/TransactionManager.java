package manager;

import dto.TransactionDTO;
import gateway.TransactionGateway;
import java.util.ArrayList;

public class TransactionManager
{
    private TransactionGateway gateway = new TransactionGateway();
    
    public boolean addTransaction(TransactionDTO transaction) {
        
        return gateway.insertTransaction(transaction);
    }
    
    public boolean removeTransaction(TransactionDTO transaction) {
        
        return gateway.deleteTransaction(transaction);
    }
    
    public TransactionDTO findTransaction(int TransactionID)
    {
        return gateway.find(TransactionID);
    }
    
    public ArrayList<TransactionDTO> viewOrderTransactions(int OrderID)
    {
        return gateway.findOrderTransactions(OrderID);
    }
}
