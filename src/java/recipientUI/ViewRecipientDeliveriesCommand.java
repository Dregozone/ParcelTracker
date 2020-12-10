package recipientUI;

import manager.OrderManager;

public class ViewRecipientDeliveriesCommand implements RecipientCommand
{    
    private final int userId;       
    private final OrderManager orderMgr;

    public ViewRecipientDeliveriesCommand(int userId)
    {
        this.userId = userId;
        orderMgr = new OrderManager();
    }

    @Override
    public Object execute()
    {
        return orderMgr.viewRecipientDeliveries(userId);
    }
}
