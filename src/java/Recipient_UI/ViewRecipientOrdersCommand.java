package Recipient_UI;

import manager.OrderManager;

public class ViewRecipientOrdersCommand implements RecipientCommand
{    
    private final int userId;       
    private final OrderManager orderMgr;

    public ViewRecipientOrdersCommand(int userId)
    {
        this.userId = userId;
        orderMgr = new OrderManager();
    }

    @Override
    public Object execute()
    {
        return orderMgr.viewRecipientOrders(userId);
    }
}
