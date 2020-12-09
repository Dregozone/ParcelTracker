package recipientUI;

import manager.OrderManager;

public class ViewDeliveryProgressCommand implements RecipientCommand
{
    private final int orderId;
    private final OrderManager orderMgr;

    public ViewDeliveryProgressCommand(int orderId)
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
