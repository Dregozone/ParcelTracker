package manager;

import dto.OrderDTO;
import dto.MetricDTO;
import dto.ParcelDTO;
import gateway.OrderGateway;
import java.util.ArrayList;

public class OrderManager
{
    private OrderGateway gateway = new OrderGateway();
    
    public boolean createOrder(OrderDTO order) {
        
        return gateway.insertOrder(order);
    }
    
    public boolean editOrder(OrderDTO order) {
        
        return gateway.updateOrder(order);
    }
    
    public boolean deleteOrder(int orderId) {
        
        return gateway.deleteOrder(orderId);
    }
    
    public OrderDTO findOrder(int OrderID)
    {
        return gateway.find(OrderID);
    }

    public ArrayList<OrderDTO> viewRecipientOrders(int UserID)
    {
        return gateway.findRecipientOrders(UserID);
    }
    
    public ArrayList<MetricDTO> viewDriverMetrics()
    {
        return gateway.findDriverMetrics();
    }
    
    public ArrayList<ParcelDTO> viewOrderParcels(int OrderID)
    {
        return gateway.findOrderParcels(OrderID);
    }
    
    public ArrayList<OrderDTO> viewAllOrders()
    {
        return gateway.findAllOrders();
    }
}
