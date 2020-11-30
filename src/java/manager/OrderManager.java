package manager;

import dto.UserDTO;
import dto.OrderDTO;
import dto.CustomerDTO;
import dto.DiscountDTO;
import gateway.OrderGateway;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderManager
{
    private OrderGateway gateway = new OrderGateway();
    
    public OrderDTO findOrder(int OrderID)
    {
        return gateway.find(OrderID);
    }

    /*
    public UserDTO findUser(String name, String addressLine1, String zipCode)
    {
        return gateway.find(name, addressLine1, zipCode);
    }
    */

    public ArrayList<OrderDTO> getOrderSummaries()
    {
        return gateway.findAllSummaries();
    }

    public boolean insertCustomer(CustomerDTO cust)
    {
        return gateway.insert(cust);
    }
}
