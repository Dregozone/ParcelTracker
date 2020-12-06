package recipientUI;

import manager.OrderManager;
import recipientUI.RecipientCommand;

public class GetOrderSumariesByUserCommand implements RecipientCommand
{    
    private final int userId;       
    private final OrderManager orderMgr;

    public GetOrderSumariesByUserCommand(int userId)
    {
        this.userId = userId;
        orderMgr = new OrderManager();
    }

    @Override
    public Object execute()
    {
        return orderMgr.getOrderSummariesByUser(userId);
    }
}
