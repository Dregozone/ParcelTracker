package recipientUI;

import manager.OrderManager;
import recipientUI.RecipientCommand;

public class FindOrderCommand implements RecipientCommand
{

    private final int orderId;
    private final OrderManager orderMgr;

    public FindOrderCommand(int orderId)
    {
        this.orderId = orderId;
        orderMgr = new OrderManager();

    }

    @Override
    public Object execute()
    {
        return orderMgr.findOrder(orderId);
    }
}
