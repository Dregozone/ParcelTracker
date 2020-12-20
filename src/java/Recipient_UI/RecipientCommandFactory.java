package Recipient_UI;

public class RecipientCommandFactory
{
    public static final int VIEW_ORDER_PROGRESS = 7;
    public static final int VIEW_RECIPIENT_ORDERS = 8;

    public static RecipientCommand createCommand(int commandType, int id)
    {
        switch (commandType)
        {
            case VIEW_ORDER_PROGRESS: /* By orderId */
                return new ViewOrderProgressCommand(id);
            case VIEW_RECIPIENT_ORDERS: /* By userId */
                return new ViewRecipientOrdersCommand(id);
            default:
                return null;
        }
    }
}
