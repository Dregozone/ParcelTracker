package sellerUI;

import dto.OrderDTO;
import dto.ParcelDTO;

public class SellerCommandFactory
{
    public static final int VIEW_ALL_USERS = 4;
    public static final int VIEW_USER = 5;
    public static final int VIEW_ORDER_PARCELS = 9;
    public static final int VIEW_ORDER_TRANSACTIONS = 10;
    public static final int CREATE_ORDER = 11;
    public static final int EDIT_ORDER = 16;
    public static final int VIEW_ALL_PARCELS = 12;
    public static final int VIEW_PARCEL = 13;
    public static final int CREATE_PARCEL = 14;
    public static final int EDIT_PARCEL = 16;
    public static final int VIEW_DRIVER_METRICS = 15;

    public static SellerCommand createCommand(int commandType)
    {
        switch (commandType)
        {
            case VIEW_ALL_USERS:
                return new ViewAllUsersCommand();
            case VIEW_ALL_PARCELS:
                return new ViewAllParcelsCommand();
            case VIEW_DRIVER_METRICS: 
                return new ViewDriverMetricsCommand();
            default:
                return null;
        }
    }

    public static SellerCommand createCommand(int commandType, int id)
    {
        switch (commandType)
        {
            case VIEW_USER: /* By userId */
                return new ViewUserCommand(id);
            case VIEW_PARCEL: /* By parcelId */
                return new ViewParcelCommand(id);
            case VIEW_ORDER_PARCELS: /* By orderId */
                return new ViewOrderParcelsCommand(id);
            case VIEW_ORDER_TRANSACTIONS: /* By orderId */ 
                return new ViewOrderTransactionsCommand(id);
            default:
                return null;
        }
    }
    
    public static SellerCommand createCommand(int commandType, OrderDTO orderDTO)
    {
        switch (commandType)
        {
            case CREATE_ORDER:
                return new CreateOrderCommand(orderDTO);
            case EDIT_ORDER:
                return new EditOrderCommand(orderDTO);
            default:
                return null;
        }
    }
    
    public static SellerCommand createCommand(int commandType, ParcelDTO parcelDTO)
    {
        switch (commandType)
        {
            case CREATE_PARCEL:
                return new CreateParcelCommand(parcelDTO);
            case EDIT_PARCEL:
                return new EditParcelCommand(parcelDTO);
            default:
                return null;
        }
    }
}
