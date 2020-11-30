package userUI;

import manager.OrderManager;

public class GetOrderSumariesCommand implements UserCommand
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
