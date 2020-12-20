package Recipient_UI;

import manager.OrderManager;

public class ViewOrderProgressCommand implements RecipientCommand
{
    private final int orderId;
    private final OrderManager orderMgr;

    public ViewOrderProgressCommand(int orderId)
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
