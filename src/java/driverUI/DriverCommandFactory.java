package driverUI;

import recipientUI.GetOrderSumariesByUserCommand;
import recipientUI.GetOrderSumariesCommand;
import recipientUI.FindOrderCommand;
import dto.OrderDTO;
import recipientUI.RecipientCommand;

public class DriverCommandFactory
{
    public static final int GET_ORDER_SUMMARIES = 6;
    public static final int FIND_ORDER_BY_ID = 7;
    public static final int GET_ORDER_SUMMARIES_BY_USER = 8;
    //public static final int CREATE_ORDER = 11;
    
    public static RecipientCommand createCommand(int commandType)
    {
        switch (commandType)
        {
            case GET_ORDER_SUMMARIES:
                return new GetOrderSumariesCommand();
            default:
                return null;
        }
    }

    public static RecipientCommand createCommand(int commandType, int id)
    {
        switch (commandType)
        {
            case FIND_ORDER_BY_ID:
                return new FindOrderCommand(id);
            case GET_ORDER_SUMMARIES_BY_USER:
                return new GetOrderSumariesByUserCommand(id);
            default:
                return null;
        }
    }
    
    public static RecipientCommand createCommand(int commandType, OrderDTO orderDTO)
    {
        switch (commandType)
        {
            //case CREATE_ORDER:
            //    return new CreateOrderCommand(orderDTO);
            default:
                return null;
        }
    }
}
