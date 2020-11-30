package managedbean;

import userUI.UserCommandFactory;
import dto.OrderDTO;
//import dto.CustomerDTO;
//import dto.UserDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "orderBean")
@SessionScoped
public class OrderBean implements Serializable
{
    //private CustomerDTO customerDetails = null;
    private OrderDTO orderDetails = null;
    //private UserDTO userDetails = null;
    private int totalOrders = 0;

    public ArrayList<OrderDTO> getOrderSummaries()
    {
        ArrayList<OrderDTO> orderSummaries
                = (ArrayList<OrderDTO>) UserCommandFactory
                        .createCommand(
                                UserCommandFactory.GET_ORDER_SUMMARIES)
                        .execute();

        totalOrders = orderSummaries.size();

        return orderSummaries;
    }
    
    public ArrayList<OrderDTO> getOrderSummariesByUser(int UserID)
    {
        ArrayList<OrderDTO> orderSummaries
                = (ArrayList<OrderDTO>) UserCommandFactory
                        .createCommand(
                                UserCommandFactory.GET_ORDER_SUMMARIES_BY_USER,
                                UserID)
                        .execute();

        totalOrders = orderSummaries.size();

        return orderSummaries;
    }

    public String fetchOrderDetails(int orderID)
    {
        orderDetails
                = (OrderDTO) UserCommandFactory
                        .createCommand(
                                UserCommandFactory.FIND_ORDER_BY_ID,
                                orderID)
                        .execute();

        return "viewOrder";
    }

    /*
    public CustomerDTO getCustomerDetails()
    {
        return customerDetails;
    }

    public UserDTO getUserDetails() {
        return userDetails;
    }
    */

    public int getTotalOrders()
    {
        return totalOrders;
    }

    /*
    public void setCustomerDetails(CustomerDTO customerDetails)
    {
        this.customerDetails = customerDetails;
    }
    */

    public OrderDTO getOrderDetails() {
        return orderDetails;
    }
}
