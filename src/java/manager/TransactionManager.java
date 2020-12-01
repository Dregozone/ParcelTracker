package manager;

import dto.UserDTO;
import dto.OrderDTO;
import dto.ParcelDTO;
import dto.TransactionDTO;
import dto.CustomerDTO;
import dto.DiscountDTO;
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
    
    public OrderDTO findOrder(int OrderID)
    {
        return gateway.find(OrderID);
    }
    
    public ArrayList<TransactionDTO> getTransactionSummariesByOrder(int OrderID)
    {
        return gateway.findAllTransactionSummariesByOrder(OrderID);
    }
    
    public ArrayList<OrderDTO> getOrderSummaries()
    {
        return gateway.findAllSummaries();
    }

    public boolean insertCustomer(CustomerDTO cust)
    {
        return gateway.insert(cust);
    }
}
