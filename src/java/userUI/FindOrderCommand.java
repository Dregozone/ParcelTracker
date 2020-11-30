package userUI;

import manager.OrderManager;

public class FindOrderCommand implements UserCommand
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
