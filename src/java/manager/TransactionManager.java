package manager;

import dto.UserDTO;
import dto.OrderDTO;
import dto.ParcelDTO;
import dto.TransactionDTO;
import gateway.TransactionGateway;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionManager
{
    private TransactionGateway gateway = new TransactionGateway();
    
    public OrderDTO findTransaction(int TransactionID)
    {
        return gateway.find(TransactionID);
    }
    
    public ArrayList<TransactionDTO> getTransactionSummariesByOrder(int OrderID)
    {
        return gateway.findAllTransactionSummariesByOrder(OrderID);
    }
    
    public boolean addTransaction(TransactionDTO transaction) {
        
        return gateway.addTransaction(transaction);
    }
    
    public boolean removeTransaction(TransactionDTO transaction) {
        
        return gateway.removeTransaction(transaction);
    }
}
