package recipientUI;

public class RecipientCommandFactory
{
    public static final int VIEW_DELIVERY_PROGRESS = 7;
    public static final int VIEW_RECIPIENT_DELIVERIES = 8;

    public static RecipientCommand createCommand(int commandType, int id)
    {
        switch (commandType)
        {
            case VIEW_DELIVERY_PROGRESS: /* By orderId */
                return new ViewDeliveryProgressCommand(id);
            case VIEW_RECIPIENT_DELIVERIES: /* By userId */
                return new ViewRecipientDeliveriesCommand(id);
            default:
                return null;
        }
    }
}
