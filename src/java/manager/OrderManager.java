package manager;

import dto.UserDTO;
import dto.OrderDTO;
import dto.MetricDTO;
import dto.ParcelDTO;
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

    public ArrayList<OrderDTO> getOrderSummariesByUser(int UserID)
    {
        return gateway.findAllSummariesByUser(UserID);
    }
    
    public ArrayList<MetricDTO> viewDriverMetrics()
    {
        return gateway.viewDriverMetrics();
    }
    
    public ArrayList<ParcelDTO> getParcelSummariesByOrder(int OrderID)
    {
        return gateway.findAllSummariesByOrder(OrderID);
    }
    
    public ArrayList<OrderDTO> getOrderSummaries()
    {
        return gateway.findAllSummaries();
    }
    
    public boolean createOrder(OrderDTO order) {
        
        return gateway.createOrder(order);
    }
    
    public boolean editOrder(OrderDTO order) {
        
        return gateway.editOrder(order);
    }
    
    public boolean deleteOrder(int orderId) {
        
        return gateway.deleteOrder(orderId);
    }
}
