package recipientUI;

import manager.OrderManager;
import recipientUI.RecipientCommand;

public class GetOrderSumariesCommand implements RecipientCommand
{

    private final OrderManager orderMgr;

    public GetOrderSumariesCommand()
    {
        orderMgr = new OrderManager();
    }

    @Override
    public Object execute()
    {
        return orderMgr.getOrderSummaries();
    }
}
